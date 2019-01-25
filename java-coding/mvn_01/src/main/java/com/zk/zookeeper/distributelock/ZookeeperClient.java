package com.zk.zookeeper.distributelock;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperClient {
	private final static String CONNECTIONSTRING = "192.168.254.134:2181";
	private static int sessionTimeout = 5000;

	// 获取连接
	public static ZooKeeper getInstance() throws IOException, InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(1);// 利用这个wait方法保存已连接状态，有延时
		ZooKeeper zooKeeper = new ZooKeeper(CONNECTIONSTRING, sessionTimeout, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				if (event.getState() == Event.KeeperState.SyncConnected) {// 判断是否已经连接上
					countDownLatch.countDown();
				}
			}
		});
		countDownLatch.await();
		return zooKeeper;
	}

	public static int getSessionTimeout() {
		return sessionTimeout;
	}

}
