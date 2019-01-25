package com.neno.designpattern.structural.bridge;

public interface Brand {
	void brand();
}

class Lenovo implements Brand {

	@Override
	public void brand() {
		// TODO Auto-generated method stub
		System.out.println("销售Lenovo品牌。。。");
	}

}

class Dell implements Brand {

	@Override
	public void brand() {
		// TODO Auto-generated method stub
		System.out.println("销售Dell品牌。。。");
	}

}

class Mac implements Brand {

	@Override
	public void brand() {
		// TODO Auto-generated method stub
		System.out.println("销售Mac品牌。。。");
	}

}