package com.clone.mvn_01.model.m3;

public class Main {
	public static void main(String[] args) throws CloneNotSupportedException {
		Address address = new Address();
		address.setType("123");
		address.setValue("hao");
		Chinese chinese = new Chinese();
		chinese.setAge(10);
		chinese.setName("zhang");
		chinese.setAddress(address);
		System.out.println(chinese);
		
		Chinese chinese2 = (Chinese) chinese.clone();
		System.out.println(chinese2);
	}
}
