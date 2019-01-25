package com.clone.executor;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CallableAndFuture {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return new Random().nextInt(99) + 100;
			}
		});
		executorService.submit(futureTask);
		executorService.shutdown();
		try {
			Thread.sleep(500);
			System.out.println(futureTask.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--------------------------------------------------");
		new CallableAndFuture1().fun();
	}
}

class CallableAndFuture1 {
	public void fun() {
		FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return new Random().nextInt(19999) + 10000;
			}
		});
		new Thread(futureTask).start();
		try {
			Thread.sleep(500);
			System.out.println(futureTask.get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
