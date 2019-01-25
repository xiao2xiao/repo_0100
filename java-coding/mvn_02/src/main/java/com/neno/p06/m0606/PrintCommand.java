package com.neno.p06.m0606;

public class PrintCommand implements Command {

	@Override
	public void process(int[] target) {
		for (int i : target) {
			System.out.print(i + "  ");
		}
		System.out.println();
	}

}