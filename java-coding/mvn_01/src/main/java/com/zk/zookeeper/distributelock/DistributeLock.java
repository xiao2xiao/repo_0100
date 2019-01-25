package com.zk.zookeeper.distributelock;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * 分布式锁实现
 */
/**
 * 所以调整后的分布式锁算法流程如下：
 * 1、客户端连接zookeeper，并在/lock下创建临时的且有序的子节点，
 * 第一个客户端对应的子节点为/lock/lock-0000000000，第二个为/lock/lock-0000000001，以此类推；
 * 2、客户端获取/lock下的子节点列表，判断自己创建的子节点是否为当前子节点列表中序号最小的子节点，如果是则认为获
 * 得锁，否则监听刚好在自己之前一位的子节点删除消息，获得子节点变更通知后重复此步骤直至获得锁； 
 * 3、执行业务代码；
 * 4、完成业务流程后，删除对应的子节点释放锁。
 * 
 * @author root
 *
 */
public class DistributeLock {
	private static final String ROOT_LOCK = "/LOCKS";// 根节点
	private ZooKeeper zooKeeper;
	private int sessionTimeout;// 会话超时时间
	private String lockID;// 记录锁节点ID
	private final static byte[] data = { 1, 2 };// 节点数据
	private CountDownLatch countDownLatch = new CountDownLatch(1);

	public DistributeLock() throws IOException, InterruptedException {
		this.zooKeeper = ZookeeperClient.getInstance();
		this.sessionTimeout = ZookeeperClient.getSessionTimeout();
	}

	// 获取锁的方法
	public boolean lock() {
		try {
			// 四个参数：路径、保存内容、权限、临时有序节点 LOCKS/0000000001
			lockID = zooKeeper.create(ROOT_LOCK + "/", data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL_SEQUENTIAL);
			System.out.println(Thread.currentThread().getName() + "-->成功创建了lock节点,节点ID=" + lockID + "开始去竞争锁");
			// 获取当前根节点下所有的节点,然后判断是不是最小节点
			List<String> childrenNodes = zooKeeper.getChildren(ROOT_LOCK, true);
			// 排序从小到大
			SortedSet<String> sortedSet = new TreeSet<String>();
			for (String children : childrenNodes) {
				sortedSet.add(ROOT_LOCK + "/" + children);
			}
			String first = sortedSet.first();// 拿到最小的节点
			if (lockID.equals(first)) {
				// 表示当前就是最小的节点
				System.out.println(Thread.currentThread().getName() + "---->成功的获取锁.lock节点为=" + lockID);
				return true;
			}
			// 拿到这个节点之前的所有节点,再拿最后一个节点，就是拿当前节点的上一个节点,用于监听变化
			SortedSet<String> lessThanLockID = sortedSet.headSet(lockID);
			if (!lessThanLockID.isEmpty()) {
				String prevLockID = lessThanLockID.last();
				zooKeeper.exists(prevLockID, new LockWatcher(countDownLatch));
				countDownLatch.await(sessionTimeout, TimeUnit.MILLISECONDS);
				// 上面这段代码意味着会话超时或者节点被删除（释放）了
				System.out.println(Thread.currentThread().getName() + "成功获取锁,lockID=" + lockID);
			}
			return true;
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 释放锁的方法
	public boolean unlock() {
		try {
			System.out.println(Thread.currentThread().getName() + "--->开始释放锁lock=" + lockID);
			zooKeeper.delete(lockID, -1);
			System.out.println("节点" + lockID + "成功被删除");
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(10);
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				DistributeLock lock = null;
				try {
					lock = new DistributeLock();
					latch.countDown();
					latch.await();
					lock.lock();
					Thread.sleep(random.nextInt(10000));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if (lock != null) {
						lock.unlock();
					}
				}
			}).start();
		}
	}
}
