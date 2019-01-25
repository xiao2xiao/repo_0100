package com.neno.p06.m0608;

import com.neno.p06.m0606.ProcessArray;

public class CommandTest2 {
	public static void main(String[] args) {
		int[] array = new int[] { -2, 1, 1, 2 };
		ProcessArray processArray = new ProcessArray();
		// processArray.process(target, new PrintCommand());
		System.out.println("+++++++++++++++++++++++++++++++");
		processArray.process(array, target -> {
			int sum = 0;
			for (int i : target) {
				sum += i;
			}
			System.out.println("数组的和：" + sum);
		});
		new Thread(() -> {
			System.out.println("how are you");
		}).start();
	}
}
