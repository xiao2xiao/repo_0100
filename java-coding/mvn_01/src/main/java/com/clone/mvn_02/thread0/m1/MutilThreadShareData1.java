package com.clone.mvn_02.thread0.m1;

class SaleTickets implements Runnable {
	public int totals = 100;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (totals > 0) {
			sale();
		}
	}

	private synchronized void sale() {
		// TODO Auto-generated method stub
		System.out.println("剩下：" + totals + "," + Thread.currentThread().getName());
		totals--;
	}

}

public class MutilThreadShareData1 {
	public static void main(String[] args) {
		SaleTickets ss = new SaleTickets();
		new Thread(ss, "t1").start();
		new Thread(ss, "t2").start();
		new Thread(ss, "t3").start();
	}
}
