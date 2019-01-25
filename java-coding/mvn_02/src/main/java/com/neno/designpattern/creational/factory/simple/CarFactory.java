package com.neno.designpattern.creational.factory.simple;

public class CarFactory {

	private static final String constants = "com.neno.designpattern.creational.factory.simple.";

	/**
	 * 1.方式一
	 * 
	 * @param type
	 * @return
	 */
	public static Car createCar(String type) {
		Car car = null;
		if ("Byd".equals(type)) {
			car = new Byd();
		} else if ("Bens".equals(type)) {
			car = new Bens();
		} else if ("Bmw".equals(type)) {
			car = new Bmw();
		}
		return car;
	}

	/**
	 * 2.方式二
	 * 
	 * @param type
	 * @return
	 */
	public static Car createByd() {
		return new Byd();
	}

	public static Car createBens() {
		return new Bens();
	}

	public static Car createBmw() {
		return new Bmw();
	}

	/**
	 * 3. 方式三,使用反射
	 */
	public static Car createCar2(String type) {
		Car car = null;
		try {
			car = (Car) Class.forName(constants + type).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return car;
	}
}
