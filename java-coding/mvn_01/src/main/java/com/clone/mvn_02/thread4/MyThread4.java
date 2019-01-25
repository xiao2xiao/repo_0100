package com.clone.mvn_02.thread4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable1 implements Callable<Object> {
	private String taskNum;

	public MyCallable1() {
		// TODO Auto-generated constructor stub
	}

	public MyCallable1(String taskNum) {
		this.taskNum = taskNum;
	}

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(">>>" + taskNum + "任务启动");
		Date dateTmp1 = new Date();
		Thread.sleep(1000);
		Date dateTmp2 = new Date();
		long time = dateTmp2.getTime() - dateTmp1.getTime();
		System.out.println(">>>" + taskNum + "任务终止");
		return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
	}

}

public class MyThread4 {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("----程序开始运行----");
		Date date1 = new Date();

		int taskSize = 5;
		// 创建一个线程池
		ExecutorService executorService = Executors.newFixedThreadPool(taskSize);

		List<Future> list = new ArrayList<>();
		for (int i = 0; i < taskSize; i++) {
			Callable callable = new MyCallable1("---->" + i);
			Future future = executorService.submit(callable);
			list.add(future);
		}
		executorService.shutdown();
		for (Future future : list) {
			System.out.println(future.get().toString());
		}
		Date date2 = new Date();
		System.out.println("----程序结束运行----，程序运行时间【" + (date2.getTime() - date1.getTime()) + "毫秒】");

	}
}
