package com.neno.designpattern.behavior.command;

public class Invoker {
	private Command command;

	public Invoker(Command command) {
		super();
		this.command = command;
	}

	public void request() {
		command.execute();
	}
}
