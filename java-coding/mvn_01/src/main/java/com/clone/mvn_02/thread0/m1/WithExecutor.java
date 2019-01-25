package com.clone.mvn_02.thread0.m1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Person implements Callable<Void> {
	private int id = -1;

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(int id) {
		super();
		this.id = id;
	}

	@Override
	public Void call() throws Exception {
		try {
			Thread.sleep(new Random(100).nextInt(5000));
			System.out.println(String.format("Child Thread %d is finished!", id));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

public class WithExecutor {
	public static void main(String[] args) {
		int taskSize = 3;
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		List<Callable<Void>> list = new ArrayList<>();
		for (int i = 0; i < taskSize; i++) {
			list.add(new Person(i));
		}
		try {
			pool.invokeAll(list);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
		System.out.println("Main finished!");
	}
}
