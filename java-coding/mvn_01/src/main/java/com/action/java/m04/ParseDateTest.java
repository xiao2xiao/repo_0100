package com.action.java.m04;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParseDateTest {

	// private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	static Object object = new Object();
	static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

	public static class ParseDate implements Runnable {

		int i = 0;

		public ParseDate(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			try {
//				synchronized (object) {
//					Date tDate = SIMPLE_DATE_FORMAT.parse("2019-01-27 16:39:" + i % 60);
//					System.out.println(Thread.currentThread().getName() + " : " + i + " : " + tDate);
//				}
				if (threadLocal.get() == null) {
					threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
				}
				Date tDate = threadLocal.get().parse("2019-01-27 16:39:" + i % 60);
				System.out.println(Thread.currentThread().getName() + " : " + i + " : " + tDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 1000; i++) {
			es.execute(new ParseDate(i));
		}
		es.shutdown();
	}
}
