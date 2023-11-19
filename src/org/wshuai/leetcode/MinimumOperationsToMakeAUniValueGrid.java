package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by Wei on 10/08/2023.
 * #2033 https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/
 */
public class MinimumOperationsToMakeAUniValueGrid {

	// time O(m * n * log(m * n)), space O(m * n)
	public int minOperations(int[][] grid, int x) {
		int res = 0, m = grid.length, n = grid[0].length, median = 0;
		int[] arr = new int[m * n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = grid[i / n][i % n];
		}
		median = findKthLargestSorting(arr, arr.length / 2 + 1);
		// median = findKthMinQueue(arr, arr.length / 2 + 1);
		// median = findKthLargestQuickSelect(arr, arr.length / 2 + 1);
		for (int i = 0; i < arr.length; i++) {
			if (Math.abs(median - arr[i]) % x != 0) {
				return -1;
			}
			res += Math.abs(median - arr[i]) / x;
		}
		return res;
	}

	// time O(n * log(n)), space O(1)
	private int findKthLargestSorting(int[] arr, int k) {
		Arrays.sort(arr);
		return arr[arr.length / 2];
	}

	// time O(n * log(k)), space O(k)
	private int findKthMinQueue(int[] arr, int k) {
		PriorityQueue<Integer> minQueue = new PriorityQueue<>();
		for (int num : arr) {
			minQueue.offer(num);
			if (minQueue.size() > k) {
				minQueue.poll();
			}
		}
		return minQueue.peek();
	}

	// time - average: O(n * log(n)), worst: O(n^2), expected: O (n) with randomization; space O(n)
	private int findKthLargestQuickSelect(int[] arr, int k) {
		int low = 0, high = arr.length - 1;
		while (low <= high) {
			int pivot = partition(arr, low, high);
			if (pivot == k - 1) {
				return arr[pivot];
			} else if (pivot < k - 1) {
				low = pivot + 1;
			} else {
				high = pivot - 1;
			}
		}
		return -1;
	}

	private int partition(int[] arr, int low, int high) {
		int pivot = low + (new Random()).nextInt(high - low + 1);
		swap(arr, pivot, high);
		pivot = low;
		for (int i = low; i < high; i++) {
			if (arr[i] >= arr[high]) {
				int temp = arr[i];
				arr[i] = arr[pivot];
				arr[pivot++] = temp;
			}
		}
		swap(arr, pivot, high);
		return pivot;
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
