package com.clone.executor;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3, new TotalTask());
		BillTask taskA = new BillTask(barrier, "taskA");
		BillTask taskB = new BillTask(barrier, "taskB");
		BillTask taskC = new BillTask(barrier, "taskC");
		new Thread(taskA).start();
		new Thread(taskB).start();
		new Thread(taskC).start();
	}
}

class TotalTask implements Runnable {
	@Override
	public void run() {
		System.out.println("所有的人到齐了...........");
	}
}

class BillTask implements Runnable {
	private CyclicBarrier barrier;
	private String workerName;

	public BillTask() {
		// TODO Auto-generated constructor stub
	}

	public BillTask(CyclicBarrier barrier, String workerName) {
		super();
		this.barrier = barrier;
		this.workerName = workerName;
	}

	@Override
	public void run() {
		System.out.println(workerName + " : is starting.........");
		try {
			Thread.sleep(new Random().nextInt(2999) + 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(workerName + " : is waiting.........");
		try {
			barrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(workerName + " : is running.........");

	}

}