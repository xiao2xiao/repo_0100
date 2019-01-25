package com.neno.designpattern.behavior.mediator;

public class Research implements Department {

	private Mediator mediator;

	public Research(Mediator mediator) {
		this.mediator = mediator;
		mediator.register("research", this);
	}

	public Mediator getMediator() {
		return mediator;
	}

	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	@Override
	public void outAction() {
		System.out.println("汇报工作！没钱了，需要资金支持！");
	}

	@Override
	public void selfAction() {
		System.out.println("研究！");
	}

}
