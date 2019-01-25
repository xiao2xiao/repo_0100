package com.zk.zookeeper.distributelock;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * 监听节点被删除事件
 */
public class LockWatcher implements Watcher {
	private CountDownLatch latch;

	public LockWatcher(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void process(WatchedEvent event) {
		if (event.getType() == Event.EventType.NodeDeleted) {// 判断是不是节点删除了
			latch.countDown();
		}
	}
}
