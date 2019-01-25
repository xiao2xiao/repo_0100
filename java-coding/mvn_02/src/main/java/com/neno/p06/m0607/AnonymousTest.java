package com.neno.p06.m0607;

public class AnonymousTest {

	public void test(Product product) {
		System.out.println("购买了一个 " + product.getName() + "，花了 " + product.getPrice() + " 元。");
	}

	public static void main(String[] args) {
		// AnonymousTest anonymousTest = new AnonymousTest();
		new AnonymousTest().test(new Product() {

			@Override
			public double getPrice() {
				// TODO Auto-generated method stub
				return 50.0;
			}

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "GPA";
			}
		});
	}
}

interface Product {
	double getPrice();

	String getName();
}