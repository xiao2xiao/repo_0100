package com.neno.designpattern.creational.factory.abt;

public interface CarFactory {
	Tyre createTyre();

	Seat createSeat();
}

class HighCarFactrory implements CarFactory {

	@Override
	public Tyre createTyre() {
		// TODO Auto-generated method stub
		return new HighTyre();
	}

	@Override
	public Seat createSeat() {
		// TODO Auto-generated method stub
		return new HighSeat();
	}

}
class LowCarFactrory implements CarFactory {

	@Override
	public Tyre createTyre() {
		// TODO Auto-generated method stub
		return new LowTyre();
	}

	@Override
	public Seat createSeat() {
		// TODO Auto-generated method stub
		return new LowSeat();
	}

}