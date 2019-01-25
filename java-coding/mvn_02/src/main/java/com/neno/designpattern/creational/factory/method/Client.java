package com.neno.designpattern.creational.factory.method;

public class Client {
	public static void main(String[] args) {
		Car car = new BydCarFatroy().createCar();
		Car car2 = new BmwCarFatroy().createCar();
		car.run();
		car2.run();
	}
}
