package com.sort.qsort;

public class QuickSort {
	static int partition(int start, int end, int[] arr) {
		int low = start, high = end;
		int temp = arr[start];
		while (low < high) {
			while (low < high && temp <= arr[high]) {
				high--;
			}
			if (low < high) {
				arr[low++] = arr[high];
			}
			while (low < high && temp > arr[low]) {
				low++;
			}
			if (low < high) {
				arr[high--] = arr[low];
			}
		}
		arr[low] = temp;
		return low;
	}

	static void qSort(int start, int end, int[] arr) {
		if (start < end) {
			int middle = partition(start, end, arr);
			qSort(start, middle - 1, arr);
			qSort(middle + 1, end, arr);
		}
	}

	public static void main(String[] args) {
		int[] arr = { 10, 11, 5, 1, 0, 2, 12, 14, 7, -1, 13, 4 };
		qSort(0, arr.length - 1, arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
