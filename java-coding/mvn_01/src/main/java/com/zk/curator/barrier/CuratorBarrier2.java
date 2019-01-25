package com.zk.curator.barrier;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 同时开始，但不一定同时结束
 * 
 * 工作的时候应该把barrier设置成一个公共模块，然后让其他模块去引用它
 * 
 * @author root
 *
 */
public class CuratorBarrier2 {
	/**
	 * zookeeper地址
	 */
	static final String CONNECT_ADDR = "192.168.254.134:2181";
	/**
	 * session超时时间 ms
	 */
	static final int SESSION_OUTTIME = 5000;

	static DistributedBarrier barrier = null;

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						CuratorFramework cf = createCuratorFramework();
						cf.start();
						barrier = new DistributedBarrier(cf, "/super");
						System.out.println(Thread.currentThread().getName() + "设置barrier!");
						/**
						 * 设置
						 */
						barrier.setBarrier();
						/**
						 * 等待
						 */
						barrier.waitOnBarrier();
						System.out.println("---------开始执行程序----------");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, "t" + i).start();
		}
		Thread.sleep(5000);
		/**
		 * 释放
		 */
		barrier.removeBarrier();
	}

	public static CuratorFramework createCuratorFramework() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
				.connectionTimeoutMs(SESSION_OUTTIME).retryPolicy(retryPolicy).build();
		return curatorFramework;
	}

}
