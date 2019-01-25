package com.neno.designpattern.creational.factory.abt;

public interface Seat {
	void how();

	void hga();
}

class HighSeat implements Seat {

	@Override
	public void how() {
		// TODO Auto-generated method stub
		System.out.println("I am HighSeat how()..........");
	}

	@Override
	public void hga() {
		// TODO Auto-generated method stub
		System.out.println("I am HighSeat hga()..........");
	}
}

class LowSeat implements Seat {

	@Override
	public void how() {
		// TODO Auto-generated method stub
		System.out.println("I am LowSeat how()..........");
	}

	@Override
	public void hga() {
		// TODO Auto-generated method stub
		System.out.println("I am LowSeat hga()..........");
	}
}