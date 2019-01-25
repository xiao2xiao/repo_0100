package com.zk.curator.barrier;

import java.util.Random;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 同时开始，同时结束
 * 
 * @author root
 *
 */
public class CuratorBarrier1 {
	/**
	 * zookeeper地址
	 */
	static final String CONNECT_ADDR = "192.168.254.134:2181";
	/**
	 * session超时时间 ms
	 */
	static final int SESSION_OUTTIME = 5000;

	public static CuratorFramework createCuratorFramework() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
				.connectionTimeoutMs(SESSION_OUTTIME).retryPolicy(retryPolicy).build();
		return curatorFramework;
	}

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						CuratorFramework cf = createCuratorFramework();
						cf.start();
						DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(cf, "/super", 5);
						Thread.sleep(1000 * (new Random()).nextInt(3));
						System.out.println(Thread.currentThread().getName() + "已经准备");
						barrier.enter();
						System.out.println("同时开始运行...");
						Thread.sleep(1000 * (new Random()).nextInt(3));
						System.out.println(Thread.currentThread().getName() + "运行完毕");
						barrier.leave();
						System.out.println("同时退出运行...");
						cf.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, "t" + i).start();
		}

	}
}
