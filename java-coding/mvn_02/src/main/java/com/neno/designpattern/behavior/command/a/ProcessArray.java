package com.neno.designpattern.behavior.command.a;

public class ProcessArray {
	public void process(int[] target, Command command) {
		command.process(target);
	}
}
