package com.clone.sort.heapsort;

/**
 * 小根堆正好是逆序排列
 */
public class SmallHeapSort {

	/**
	 * 初始化堆
	 * 
	 * @param arr
	 * @param length
	 */
	static void initHeap(int[] arr, int length) {
		for (int i = length / 2 - 1; i > -1; i--) {
			heapAdjust(arr, i, length);
			// System.out.println("第 " + i + " 次调整：");
			// for (int j : arr) {
			// System.out.print(j + " ");
			// }
			// System.out.println();
		}
	}

	/**
	 * 堆排序
	 * 
	 * @param arr
	 * @param length
	 */
	static void heapSort(int[] arr, int length) {
		initHeap(arr, length);
		for (int i = length - 1; i > -1; i--) {
			swap(arr, i, 0);
			heapAdjust(arr, 0, i);
			System.out.println("第 " + i + " 次排序：");
			for (int j : arr) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 调整堆
	 * 
	 * @param arr
	 * @param i
	 * @param length
	 */
	static void heapAdjust(int[] arr, int i, int length) {
		int temp = arr[i];
		for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
			if (k + 1 < length && arr[k] > arr[k + 1]) {
				k++;
			}
			if (temp > arr[k]) {
				swap(arr, i, k);
				i = k;
			} else {
				break;
			}
		}
	}

	/**
	 * 交换
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	static void swap(int arr[], int i, int j) {
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
		// qsort(0, arr.length - 1, arr);
		heapSort(arr, arr.length);
		System.out.println();
		System.out.println("排序之后：");
		for (int i : arr) {
			System.out.print(i + "  ");
		}
		System.out.println();
	}
}
