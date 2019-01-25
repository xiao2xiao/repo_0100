package com.zk.curator.cluster;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class Test {
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

		// 2 通过工厂创建连接
		CuratorFramework cf = createCuratorFramework();
		// 3 开启连接
		cf.start();

		Thread.sleep(3000);
		System.out.println(cf.getChildren().forPath("/super").get(0));

		// 4 创建节点
		// Thread.sleep(1000);
		cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/super/c1", "c1内容".getBytes());
		Thread.sleep(1000);
		// cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/super/c2","c2内容".getBytes());
		// Thread.sleep(1000);
		//
		//
		//
		// //5 读取节点
		// Thread.sleep(1000);
		// String ret1 = new String(cf.getData().forPath("/super/c1"));
		// System.out.println(ret1);
		//
		//
		// //6 修改节点
		Thread.sleep(1000);
		cf.setData().forPath("/super/c2", "修改的新c2内容".getBytes());
		String ret2 = new String(cf.getData().forPath("/super/c2"));
		System.out.println(ret2);
		//
		//
		//
		// //7 删除节点
		// Thread.sleep(1000);
		// cf.delete().forPath("/super/c1");

	}
}
