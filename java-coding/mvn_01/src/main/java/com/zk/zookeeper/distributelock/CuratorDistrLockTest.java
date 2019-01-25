package com.zk.zookeeper.distributelock;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

public class CuratorDistrLockTest {

	/** Zookeeper info */
	private final static String ZK_ADDRESS = "192.168.254.134:2181";
	private static final String ZK_LOCK_PATH = "/LOCKS";

	public static void main(String[] args) throws InterruptedException {
		// 1.Connect to zk
		CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, new RetryNTimes(10, 5000));
		client.start();
		System.out.println("zk client start successfully!");

		Thread t1 = new Thread(() -> {
			doWithLock(client);
		}, "t1");
		Thread t2 = new Thread(() -> {
			doWithLock(client);
		}, "t2");
		Thread t3 = new Thread(() -> {
			doWithLock(client);
		}, "t3");

		t1.start();
		t2.start();
		t3.start();
	}

	private static void doWithLock(CuratorFramework client) {
		// Curator提供的InterProcessMutex是分布式锁的实现。通过acquire获得锁，并提供超时机制，release方法用于释放锁。
		InterProcessMutex lock = new InterProcessMutex(client, ZK_LOCK_PATH);
		try {
			if (lock.acquire(10 * 1000, TimeUnit.SECONDS)) {
				System.out.println(Thread.currentThread().getName() + " hold lock");
				Thread.sleep(5000L);
				System.out.println(Thread.currentThread().getName() + " release lock");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				lock.release();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
