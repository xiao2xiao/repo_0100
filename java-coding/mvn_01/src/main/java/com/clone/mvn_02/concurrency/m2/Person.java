package com.clone.mvn_02.concurrency.m2;

public class Person<T> implements Comparable<T> {
	private int id;
	private int age;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
