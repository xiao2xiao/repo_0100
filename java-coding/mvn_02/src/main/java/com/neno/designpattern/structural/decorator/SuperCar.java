package com.neno.designpattern.structural.decorator;

public class SuperCar implements Icar {
	protected Icar icar;

	public SuperCar(Icar icar) {
		this.icar = icar;
	}

	@Override
	public void run() {
		icar.run();
	}

}

class FlyCar extends SuperCar {

	public FlyCar(Icar icar) {
		super(icar);
	}

	public void fly() {
		System.out.println("在天上飞。。。。。。。");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		fly();
	}
}

class WaterCar extends SuperCar {

	public WaterCar(Icar icar) {
		super(icar);
	}

	public void water() {
		System.out.println("在水里游。。。。。。。");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		water();
	}
}

class AutoCar extends SuperCar {

	public AutoCar(Icar icar) {
		super(icar);
		// TODO Auto-generated constructor stub
	}

	public void auto() {
		System.out.println("自动驾驶。。。。。。。");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		auto();
	}
}