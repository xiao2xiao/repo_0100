package com.clone.mvn_04.abstract01;

public class Tst {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Animal animal1 = new Animal() {

			@Override
			public void fun() {
				// TODO Auto-generated method stub
				System.out.println("fun2..............");
			}

			@Override
			public void fly() {
				// TODO Auto-generated method stub
				System.out.println("fly2................");
			}

		};
		Animal animal = new Animal() {

			@Override
			public void fun() {
				// TODO Auto-generated method stub

				System.out.println("fun..............");
			}

			@Override
			public void fly() {
				// TODO Auto-generated method stub
				System.out.println("fly................");
			}
		};
	}
}
