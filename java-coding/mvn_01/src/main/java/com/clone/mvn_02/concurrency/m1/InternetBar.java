package com.clone.mvn_02.concurrency.m1;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author root
 *
 */
class People implements Delayed {

	private String name;
	private String id;// 身份证
	private long endTime;// 截止时间

	public People() {

	}

	public People(String name, String id, long endTime) {
		super();
		this.name = name;
		this.id = id;
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	/**
	 * 相互比较，排序使用
	 */
	@Override
	public int compareTo(Delayed o) {
		People people = (People) o;
		return endTime - people.getEndTime() > 0 ? 1 : 0;
	}

	/**
	 * 
	 * 用来判断是否到了截止时间
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return endTime - System.currentTimeMillis();
	}

}

public class InternetBar implements Runnable {
	private DelayQueue<People> queue = new DelayQueue<>();
	public boolean yingYe = true;

	public void getIn(String name, String id, int money) {
		People people = new People(name, id, 1000 * 60 * money + System.currentTimeMillis());
		System.out.println("网民：" + people.getName() + "，身份证：" + people.getId() + "，交钱：" + money + "元，开始上机了！");
		this.queue.add(people);
	}

	public void getOff(People people) {
		System.out.println("网民：" + people.getName() + "，身份证：" + people.getId() + "，时间到下机！");
	}

	@Override
	public void run() {
		while (yingYe) {
			try {
				System.out.println("Checking...............");
				People people = queue.take();
				getOff(people);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("网吧开始营业.............");
		InternetBar iBar = new InternetBar();
		new Thread(iBar).start();
		iBar.getIn("aaa", "123", 1);
		iBar.getIn("bbb", "124", 4);
		iBar.getIn("ddd", "121", 3);
		iBar.getIn("ccc", "122", 2);
	}
}
