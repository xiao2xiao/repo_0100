package com.clone.mvn_01.model.m2;

public class Person implements Cloneable {
	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();

	}

	@Override
	public String toString() {
		System.out.println(super.toString());
		return "Person [name=" + name + ", age=" + age + "]";
	}

}
