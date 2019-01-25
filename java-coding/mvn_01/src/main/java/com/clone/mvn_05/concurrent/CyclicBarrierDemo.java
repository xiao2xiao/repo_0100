package com.clone.mvn_05.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	public static void main(String[] args) throws InterruptedException {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new TotalTask());
		new Thread(new BillTask("task1", cyclicBarrier)).start();
		new Thread(new BillTask("task2", cyclicBarrier)).start();
		//Thread.sleep(10000);
		new Thread(new BillTask("task3", cyclicBarrier)).start();
		System.out.println("Main thread end!");
	}
}

class TotalTask implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("所有子任务执行完了，开始执行主任务了");
	}
}

class BillTask implements Runnable {

	private String billName;
	private CyclicBarrier cyclicBarrier;

	public BillTask() {
		// TODO Auto-generated constructor stub
	}

	public BillTask(String billName, CyclicBarrier cyclicBarrier) {
		super();
		this.billName = billName;
		this.cyclicBarrier = cyclicBarrier;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public CyclicBarrier getCyclicBarrier() {
		return cyclicBarrier;
	}

	public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {

		try {
			System.out.println("市区：" + billName + " 运算开始！");
			Thread.sleep(1000);
			System.out.println("市区：" + billName + " 运算结束，等待中......");
			cyclicBarrier.await();
			System.out.println("全部结束，市区：" + billName + " 才开始后面的工作。");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}