package com.masterworker2;

import java.util.Random;

public class Client {
	public static void main(String[] args) {
		int workerCount = Runtime.getRuntime().availableProcessors();
		// int workerCount = 20;
		Master master = new Master(new Worker(), workerCount);
		for (int i = 1; i < 100; i++) {
			Task task = new Task();
			task.setId(i);
			task.setPrice(new Random().nextInt(1000));
			master.submit(task);
		}
		master.execute();
		long start = System.currentTimeMillis();
		while (true) {
			if (master.isComplete()) {
				long end = System.currentTimeMillis();
				System.out.println("共有 " + workerCount + " 处理器，总共耗时 ：" + (end - start));
				int ret = master.getResult();
				System.out.println("结果是 ： " + ret);
				break;
			}
		}
	}
}
