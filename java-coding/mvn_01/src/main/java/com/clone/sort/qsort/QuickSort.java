package com.clone.sort.qsort;

public class QuickSort {

	static int partition(int start, int end, int[] arr) {
		int i = start, j = end;
		int temp = arr[start];
		while (i < j) {
			while (i < j && temp <= arr[j]) {
				j--;
			}
			if (i < j) {
				arr[i++] = arr[j];
			}
			while (i < j && temp > arr[i]) {
				i++;
			}
			if (i < j) {
				arr[j--] = arr[i];
			}
		}
		arr[i] = temp;
		return i;
	}

	static void qsort(int start, int end, int[] arr) {
		if (start < end) {
			int middle = partition(start, end, arr);
			qsort(start, middle - 1, arr);
			qsort(middle + 1, end, arr);
		}
	}

	static void qsort2(int start, int end, int[] arr) {
		if (start < end) {
			int i = start, j = end;
			int temp = arr[start];
			while (i < j) {
				while (i < j && temp < arr[j]) {
					j--;
				}
				if (i < j) {
					arr[i++] = arr[j];
				}
				while (i < j && temp > arr[i]) {
					i++;
				}
				if (i < j) {
					arr[j--] = arr[i];
				}
			}
			arr[i] = temp;
			qsort2(start, i - 1, arr);
			qsort2(i + 1, end, arr);
		}
	}

	public static void main(String[] args) {
		int[] arr = { 5, 3, 1, 6, 4, 6, 7, 0, 10, -2, 10, -1, 8 };
		System.out.println("排序之前：");
		for (int i : arr) {
			System.out.print(i + "  ");
		}
		// qsort(0, arr.length - 1, arr);
		qsort2(0, arr.length - 1, arr);
		System.out.println();
		System.out.println("排序之后：");
		for (int i : arr) {
			System.out.print(i + "  ");
		}
		System.out.println();
	}

}
