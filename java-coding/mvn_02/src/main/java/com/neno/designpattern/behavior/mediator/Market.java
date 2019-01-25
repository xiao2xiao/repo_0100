package com.neno.designpattern.behavior.mediator;

public class Market implements Department {

	private Mediator mediator;

	public Market(Mediator mediator) {
		this.mediator = mediator;
		mediator.register("market", this);
	}

	public Mediator getMediator() {
		return mediator;
	}

	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	@Override
	public void outAction() {
		System.out.println("汇报工作！项目承接的进度，需要资金支持！");

		// mediator.command("finacial");

	}

	@Override
	public void selfAction() {
		System.out.println("跑去接项目！");
	}

}
