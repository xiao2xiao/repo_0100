package com.neno.designpattern.creational.factory.method;

public interface CarFactory {
	Car createCar();
}

class BensCarFatroy implements CarFactory {
	@Override
	public Car createCar() {
		// TODO Auto-generated method stub
		return new Bens();
	}
}

class BydCarFatroy implements CarFactory {
	@Override
	public Car createCar() {
		// TODO Auto-generated method stub
		return new Byd();
	}
}

class BmwCarFatroy implements CarFactory {
	@Override
	public Car createCar() {
		// TODO Auto-generated method stub
		return new Bmw();
	}
}