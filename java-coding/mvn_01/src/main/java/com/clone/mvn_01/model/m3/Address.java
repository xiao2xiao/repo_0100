package com.clone.mvn_01.model.m3;

public class Address implements Cloneable {
	private String type;
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		System.out.println("Chinese 类：" + super.toString());
		return "Address [type=" + type + ", value=" + value + "]";
	}

}
