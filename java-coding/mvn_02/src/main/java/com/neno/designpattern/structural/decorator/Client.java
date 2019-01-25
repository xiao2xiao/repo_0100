package com.neno.designpattern.structural.decorator;

public class Client {
	public static void main(String[] args) {
		Icar car = new Car();
		car.run();
		car = new WaterCar(new Car());
		car.run();
		car = new FlyCar(new AutoCar(new WaterCar(new Car())));
		car.run();
	}
}
