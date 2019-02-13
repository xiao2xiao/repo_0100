package com.neno.designpattern.behavior.command.a;

public class TestMain {
	public static void main(String[] args) {
		int[] arr = { 1, 5, 3, 2 };
		new ProcessArray().process(arr, a -> {
			int m = 0;
			for (int b : a) {
				m += b;
			}
			System.out.println(m);
		});
	}
}
