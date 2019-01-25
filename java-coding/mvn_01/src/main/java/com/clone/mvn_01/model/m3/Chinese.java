package com.clone.mvn_01.model.m3;

import com.clone.mvn_01.model.m3.Address;

public class Chinese implements Cloneable {
	private String name;
	private Integer age;
	private Address address;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Chinese oChinese = (Chinese) super.clone();
		Address address = oChinese.getAddress();
		oChinese.setAddress((Address) address.clone());
		return oChinese;
	}

	@Override
	public String toString() {
		System.out.println("Person 类：" + super.toString());
		return "Chinese [name=" + name + ", age=" + age + ", address=" + address + "]";
	}

}
