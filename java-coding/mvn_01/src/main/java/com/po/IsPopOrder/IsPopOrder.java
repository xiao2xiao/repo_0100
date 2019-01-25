package com.po.IsPopOrder;

import java.util.Stack;

public class IsPopOrder {

	public static boolean testPopOrder(int[] pushA, int[] popA) {
		if (pushA.length == 0 || pushA.length != popA.length)
			return false;
		Stack<Integer> stack = new Stack<>();
		int popIndex = 0;
		for (int i = 0; i < pushA.length; i++) {
			stack.push(pushA[i]);
			while (!stack.empty() && stack.peek() == popA[popIndex]) {
				stack.pop();
				popIndex++;
			}
		}
		return stack.empty();
	}

	public static void main(String[] args) {
		int[] pushA = { 1, 2, 3, 4, 5 };
		int[] popA = { 4, 5, 3, 2, 1, 5 };
		System.out.println(testPopOrder(pushA, popA));
	}
}