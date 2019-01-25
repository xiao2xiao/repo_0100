package com.neno.designpattern.creational.factory.abt;

public class Client {
	public static void main(String[] args) {
		Seat seat = new HighCarFactrory().createSeat();
		seat.hga();
		seat.how();
	}
}
