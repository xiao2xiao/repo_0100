package com.clone.mvn_01.test;

public abstract class AAbstract {
	private int id;
	static {
		System.out.println("123");
	}

	public AAbstract(int id) {
		super();
		this.setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
