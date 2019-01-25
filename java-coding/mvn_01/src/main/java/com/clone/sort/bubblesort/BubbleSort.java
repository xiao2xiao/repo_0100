package com.clone.sort.bubblesort;

public class BubbleSort {
	static void bubbleSort(int[] arr, int length) {
		/**
		 * 记录是否发生改变
		 */
		boolean exchange = true;
		/**
		 * 记录变化的位置
		 */
		int postion = length - 1;
		int m = 0;
		while (exchange) {
			exchange = false;
			/**
			 * 临时变量k
			 */
			int k = postion;
			for (int i = 0; i < k; i++) {
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
					postion = i;
					exchange = true;
				}
			}
			m++;
			System.out.println("第 " + m + " 次排序：");
			for (int n : arr) {
				System.out.print(n + "  ");
			}
			System.out.println();
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) {
		int[] arr = { 5, 3, 1, 6, 4, 6, 7, 0, 10, -2, 10, -1, 8 };
		System.out.println("排序之前：");
		for (int i : arr) {
			System.out.print(i + "  ");
		}
		System.out.println();
		bubbleSort(arr, arr.length);
		System.out.println();
		System.out.println("排序之后：");
		for (int i : arr) {
			System.out.print(i + "  ");
		}
		System.out.println();
	}
}
