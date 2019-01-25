package com.neno.designpattern.behavior.command;

public interface Command {
	void execute();
}

class ConcreteCommand implements Command {

	private Receiver receiver;

	public ConcreteCommand(Receiver receiver) {
		super();
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.action();
	}

}