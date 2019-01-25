package com.clone.sort.heapsort;

public class BigHeapSort {

	/**
	 * 进行排序
	 * 
	 * @param arr
	 * @param length
	 */
	static void heapSort(int[] arr, int length) {
		heapInit(arr, length);
		for (int i = length - 1; i > -1; i--) {
			swap(arr, i, 0);
			heapAdjust(arr, 0, i);
		}
	}

	/**
	 * 堆初始化
	 * 
	 * @param arr
	 * @param length
	 */
	static void heapInit(int[] arr, int length) {
		for (int i = length / 2 - 1; i > -1; i--) {
			heapAdjust(arr, i, length);
		}
	}

	/**
	 * 堆调整
	 * 
	 * @param arr
	 * @param i
	 * @param length
	 */
	static void heapAdjust(int[] arr, int i, int length) {
		int temp = arr[i];
		for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
			if (k + 1 < length && arr[k] < arr[k + 1]) {
				k++;
			}
			if (temp < arr[k]) {
				swap(arr, i, k);
				i = k;
			} else {
				break;
			}
		}
	}

	/**
	 * 数据交换
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	static void swap(int[] arr, int i, int j) {
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
		heapSort(arr, arr.length);
		System.out.println();
		System.out.println("排序之后：");
		for (int i : arr) {
			System.out.print(i + "  ");
		}
		System.out.println();
	}
}
