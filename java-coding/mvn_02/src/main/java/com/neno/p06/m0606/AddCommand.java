package com.neno.p06.m0606;

public class AddCommand implements Command {

	@Override
	public void process(int[] target) {
		int sum = 0;
		for (int i : target) {
			sum += i;
		}
		System.out.println("数组的和：" + sum);
	}

}