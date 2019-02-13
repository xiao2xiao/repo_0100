package com.neno.c;

import java.util.ArrayDeque;

public class PracticeTest {
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5, 5, 2, 7, 8, 1 };
		int[] c = revOrderArray(a);
		for (int i : c) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("+++++++++++++++++++++++++++++");
		ListNode head = createListNodes();
		ListNode aListNode = findKthToTail(head, 8);
		System.out.println(aListNode.value);

		System.out.println("+++++++++++++++++++++++++++++");
		ListNode listNode1 = createListNodes();
		ListNode listNode2 = createListNodes();
		ListNode listNode3 = merge(listNode1, listNode2);
		ListNode p = listNode3;
		while (p != null) {
			System.out.print(p.value + " ");
			p = p.next;
		}
		System.out.println();
		System.out.println("++++++++++++++++++++++++++++++");

		head = createListNodes();
		p = reverseList(head);
		while (p != null) {
			System.out.print(p.value + " ");
			p = p.next;
		}
		System.out.println();
		System.out.println("++++++++++++++++++++++++++++++");

		QueneBy2Stack.push(5);
		QueneBy2Stack.push(7);
		System.out.println(QueneBy2Stack.aDeque1 + "_" + QueneBy2Stack.aDeque2);
		System.out.println(QueneBy2Stack.pop());
		System.out.println(QueneBy2Stack.aDeque1 + "_" + QueneBy2Stack.aDeque2);
		QueneBy2Stack.push(8);
		System.out.println(QueneBy2Stack.aDeque1 + "_" + QueneBy2Stack.aDeque2);
		System.out.println("+++++++++++++++++++++++++++++++++++");
		int[] a11 = { 1, 2, 3, 4, 5 };
		int[] a12 = { 4, 5, 3, 2, 1 };
		System.out.println(IsPopOrder(a11, a12));
	}

	/**
	 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
	 * 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
	 * 
	 * @param arr
	 */
	static int[] revOrderArray(int[] arr) {
		// 存放奇数的个数
		int oddCount = 0;
		for (int i = 0; i < arr.length; i++) {
			if ((arr[i] & 1) == 1) {
				oddCount++;
			}
		}
		System.out.println("oddCount = " + oddCount);
		int[] b = new int[arr.length];
		int c = 0;
		for (int i = 0; i < arr.length; i++) {
			if ((arr[i] & 1) == 1) {
				b[c++] = arr[i];
			} else {
				b[oddCount++] = arr[i];
			}
		}
		return b;
	}

	/**
	 * 链表节点
	 * 
	 * @author root
	 *
	 */
	static class ListNode {
		int value = 0;
		ListNode next = null;

		public ListNode(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public ListNode getNext() {
			return next;
		}

		public void setNext(ListNode next) {
			this.next = next;
		}

	}

	/**
	 * 创建链表
	 * 
	 * @return
	 */
	static ListNode createListNodes() {
		ListNode a1 = new ListNode(1);
		ListNode a2 = new ListNode(2);
		ListNode a3 = new ListNode(3);
		ListNode a4 = new ListNode(4);
		ListNode a5 = new ListNode(5);
		ListNode a6 = new ListNode(6);
		ListNode a7 = new ListNode(7);
		ListNode a8 = new ListNode(8);
		ListNode a9 = new ListNode(9);
		a1.setNext(a2);
		a2.setNext(a3);
		a3.setNext(a4);
		a4.setNext(a5);
		a5.setNext(a6);
		a6.setNext(a7);
		a7.setNext(a8);
		a8.setNext(a9);
		return a1;
	}

	/**
	 * 倒数第k个节点
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	static ListNode findKthToTail(ListNode head, int k) {
		ListNode p, q;
		p = q = head;
		// 暂存k的值
		int m = k;
		// 记录节点的总数
		int count = 0;
		while (p != null) {
			p = p.next;
			count++;
			if (m-- <= 0) {
				q = q.next;
			}
		}
		if (count < k) {
			return null;
		}
		return q;
	}

	/**
	 * 反转链表
	 * 
	 * @param head
	 * @return
	 */
	static ListNode reverseList(ListNode head) {
		ListNode p = head, pre;
		pre = null;
		while (p != null) {
			ListNode q = p.next;
			p.next = pre;
			pre = p;
			p = q;
		}
		head = pre;
		return head;
	}

	/**
	 * 合并链表
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	static ListNode merge(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		if (list1.value <= list2.value) {
			list1.next = merge(list1.next, list2);
			return list1;
		} else {
			list2.next = merge(list1, list2.next);
			return list2;
		}
	}

	/**
	 * 两个栈模拟队列
	 */
	static class QueneBy2Stack {
		static ArrayDeque<Integer> aDeque1 = new ArrayDeque<>();
		static ArrayDeque<Integer> aDeque2 = new ArrayDeque<>();

		/**
		 * 入队
		 * 
		 * @param m
		 */
		public static void push(int m) {
			aDeque1.push(m);
		}

		/**
		 * 出队
		 */
		public static int pop() {
			if (aDeque1.isEmpty() && aDeque2.isEmpty()) {
				throw new RuntimeException("quene is none");
			}
			if (aDeque2.isEmpty()) {
				while (!aDeque1.isEmpty()) {
					aDeque2.push(aDeque1.pop());
				}
			}
			return aDeque2.pop();
		}
	}

	/**
	 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
	 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压
	 * 栈序列的弹出序列。（注意：这两个序列的长度是相等的）
	 */
	/**
	 * 
	 * 借用一个辅助的栈，遍历压栈顺序，先讲第一个放入栈中，这里是1，然后判断栈顶元素是不是出栈顺序的第一个元素，这里是4，
	 * 很显然1≠4，所以我们继续压栈，直到相等以后开始出栈，出栈一个元素，则将出栈顺序向后移动一位，直到不相等，这样循环等
	 * 压栈顺序遍历完成，如果辅助栈还不为空，说明弹出序列不是该栈的弹出顺序。
	 */
	static boolean IsPopOrder(int[] pushA, int[] popA) {
		if (pushA.length == 0 || popA.length == 0) {
			return false;
		}
		ArrayDeque<Integer> aDeque = new ArrayDeque<>();
		// 用于标识弹栈的序列位置
		int popIndex = 0;
		for (int i = 0; i < pushA.length; i++) {
			aDeque.push(pushA[i]);
			while (!aDeque.isEmpty() && aDeque.peek() == popA[popIndex]) {
				// 弹栈
				aDeque.pop();
				// 标识后移
				popIndex++;
			}
		}
		return aDeque.isEmpty();
	}
}
