package com.neno.designpattern.behavior.command;

public class Client {
	public static void main(String[] args) {
		Command command = new ConcreteCommand(new Receiver());
		Invoker invoker = new Invoker(command);
		invoker.request();
	}
}
