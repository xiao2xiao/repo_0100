package com.masterworker;

import java.util.Random;

public class Client {
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
		Master master = new Master(new Worker(), Runtime.getRuntime().availableProcessors());
		for (int i = 1; i <= 100; i++) {
			Task task = new Task();
			task.setId(i);
			task.setName("task - " + i);
			task.setPrice(new Random().nextInt(1000));
			master.submit(task);
		}
		master.execute();
		long start = System.currentTimeMillis();
		while (true) {
			if (master.isComplete()) {
				long end = System.currentTimeMillis();
				int ret = master.getResult();
				System.out.println("ret : " + ret + " ,运行时长：" + (end - start));
				break;
			}
		}
	}
}
