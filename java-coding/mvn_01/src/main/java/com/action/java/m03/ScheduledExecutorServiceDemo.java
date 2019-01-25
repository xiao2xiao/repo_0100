package com.action.java.m03;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {
	public static void main(String[] args) {
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
		// ses.scheduleAtFixedRate(() -> {
		// try {
		// Thread.sleep(1000);
		// System.out.println(System.currentTimeMillis() / 1000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }, 0, 2, TimeUnit.SECONDS);

		// 延误时间为init+间隔时间*i
		ses.scheduleWithFixedDelay(() -> {
			try {
				Thread.sleep(1000);
				System.out.println(System.currentTimeMillis() / 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, 1, 2, TimeUnit.SECONDS);
	}
}
