package com.neno.designpattern.structural.bridge;

public class Client {
	public static void main(String[] args) {
		Computer computer = new LapTop(new Lenovo());
		computer.sale();
		computer = new DeskTop(new Dell());
		computer.sale();
	}
}
