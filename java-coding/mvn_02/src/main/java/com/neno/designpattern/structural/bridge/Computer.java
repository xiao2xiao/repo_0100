package com.neno.designpattern.structural.bridge;

public abstract class Computer {
	protected Brand brand;

	public Computer(Brand brand) {
		this.brand = brand;
	}

	public void sale() {
		brand.brand();
	}
}

class LapTop extends Computer {

	public LapTop(Brand brand) {
		super(brand);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sale() {
		super.sale();
		System.out.println("销售LapTop品牌。。。");
	}

}

class DeskTop extends Computer {

	public DeskTop(Brand brand) {
		super(brand);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sale() {
		super.sale();
		System.out.println("销售DeskTop品牌。。。");
	}

}