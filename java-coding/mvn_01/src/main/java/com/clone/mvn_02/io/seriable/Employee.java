package com.clone.mvn_02.io.seriable;

import java.io.Serializable;

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	public String name;
	public String address;
	public transient int SSN;
	public int number;
	public static int num = 1;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSSN() {
		return SSN;
	}

	public void setSSN(int sSN) {
		SSN = sSN;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void mailCheck() {
		System.out.println("Mailing a check to " + name + " " + address);
	}
}
