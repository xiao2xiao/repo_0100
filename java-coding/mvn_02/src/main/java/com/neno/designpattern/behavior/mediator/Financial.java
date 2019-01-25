package com.neno.designpattern.behavior.mediator;

public class Financial implements Department {

	private Mediator mediator;

	public Financial(Mediator mediator) {
		this.mediator = mediator;
		mediator.register("financial", this);
	}

	public Mediator getMediator() {
		return mediator;
	}

	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	@Override
	public void outAction() {
		System.out.println("汇报工作！没钱了，钱太多了！怎么花?");
	}

	@Override
	public void selfAction() {
		System.out.println("数钱！");
	}

}
