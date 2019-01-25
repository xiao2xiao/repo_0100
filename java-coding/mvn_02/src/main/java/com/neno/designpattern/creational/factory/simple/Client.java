package com.neno.designpattern.creational.factory.simple;

public class Client {
	public static void main(String[] args) {
		Car car = CarFactory.createCar("Byd");
		car.run();
		
		Car car2 = CarFactory.createCar2("Byd");
		car2.run();
	}
}
