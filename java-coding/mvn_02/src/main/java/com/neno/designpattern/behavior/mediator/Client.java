package com.neno.designpattern.behavior.mediator;

public class Client {
	public static void main(String[] args) {
		Mediator m = new President();

		Market market = new Market(m);
		Research devp = new Research(m);
		Financial f = new Financial(m);

		market.selfAction();
		market.outAction();

		devp.selfAction();
		f.outAction();
	}
}
