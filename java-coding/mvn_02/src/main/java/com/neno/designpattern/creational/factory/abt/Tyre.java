package com.neno.designpattern.creational.factory.abt;

public interface Tyre {
	void createTyre();
}

class LowTyre implements Tyre {

	@Override
	public void createTyre() {
		// TODO Auto-generated method stub
		System.out.println("I am LowTyre...........");
	}

}

class HighTyre implements Tyre {

	@Override
	public void createTyre() {
		// TODO Auto-generated method stub
		System.out.println("I am HighTyre...........");
	}

}