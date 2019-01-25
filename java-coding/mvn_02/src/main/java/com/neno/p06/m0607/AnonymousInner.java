package com.neno.p06.m0607;

public class AnonymousInner {

	public void test(Device device) {
		System.out.println("购买了一个 " + device.getName() + "，花了 " + device.getPrice() + " 元。");
	}

	public static void main(String[] args) {
		// AnonymousTest anonymousTest = new AnonymousTest();
		new AnonymousInner().test(new Device() {
			@Override
			public double getPrice() {
				// TODO Auto-generated method stub
				return 50.0;
			}
		});

		new AnonymousInner().test(new Device() {

			{
				System.out.println("初始化代码块。。。。");
			}

			@Override
			public double getPrice() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "展示";
			}
		});
	}
}

abstract class Device {
	private String name;

	public abstract double getPrice();

	public Device() {
		// TODO Auto-generated constructor stub
	}

	public Device(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}