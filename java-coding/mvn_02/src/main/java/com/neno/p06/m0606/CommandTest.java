package com.neno.p06.m0606;

public class CommandTest {
	public static void main(String[] args) {
		int[] target = new int[] { -2, -1, 1, 2 };
		ProcessArray processArray = new ProcessArray();
		processArray.process(target, new PrintCommand());
		System.out.println("+++++++++++++++++++++++++++++++");
		processArray.process(target, new AddCommand());
	}
}
