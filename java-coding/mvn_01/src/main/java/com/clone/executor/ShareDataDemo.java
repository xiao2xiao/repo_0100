package com.clone.executor;

public class ShareDataDemo {
	public static void main(String[] args) {
		SaleTickets saleTickets = new SaleTickets();
		new Thread(saleTickets).start();
		new Thread(saleTickets).start();
		new Thread(saleTickets).start();
	}
}

class SaleTickets implements Runnable {
	private int allTicketCount = 20;

	@Override
	public void run() {
		while (allTicketCount > 0) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sale();
		}

	}

	private synchronized void sale() {
		System.out.println(Thread.currentThread().getName() + " 剩下：" + allTicketCount);
		allTicketCount--;
	}
}