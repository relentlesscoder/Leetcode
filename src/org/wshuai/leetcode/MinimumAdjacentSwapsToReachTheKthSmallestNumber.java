package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 09/27/2023.
 * #1850 https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/
 */
public class MinimumAdjacentSwapsToReachTheKthSmallestNumber {

	// time O(k * n + n * log(n)), space O(n)
	public int getMinSwaps(String num, int k) {
		int n = num.length();
		int[] nums = new int[n], target = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = num.charAt(i) - '0';
			target[i] = nums[i];
		}
		while (k-- > 0) { // O(k)
			nextPermutation(target); // O(n)
		}
		return minSwap(nums, target); // O(n * log(n))
	}

	// time O(n * log(n)), space O(n)
	private int minSwap(int[] source, int[] target) {
		// #0493 reverse pairs
		// https://leetcode.cn/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/solutions/755464/qiu-xia-kge-pai-lie-ni-xu-shu-by-love-a-ynrpf/
		int res = 0, n = source.length;
		Deque<Integer>[] queues = new ArrayDeque[10];
		Arrays.setAll(queues, i -> new ArrayDeque<>());
		for (int i = 0; i < n; i++) { // O(n)
			queues[source[i]].offer(i);
		}
		int[] idx = new int[n];
		for (int i = 0; i < n; i++) { // O(n)
			idx[i] = queues[target[i]].poll();
		}
		BIT bit = new BIT(n);
		for (int i = 0; i < n; i++) { // O(n)
			int index = idx[i] + 1;
			res += i - bit.query(index); // O(log(n))
			bit.update(index); // O(log(n))
		}
		return res;
	}

	private class BIT {

		private int[] tree;

		public BIT(int n) {
			tree = new int[n + 1];
		}

		public void update(int index) {
			while (index < tree.length) {
				tree[index]++;
				index += index & -index;
			}
		}

		public int query(int index) {
			int res = 0;
			while (index > 0) {
				res += tree[index];
				index -= index & -index;
			}
			return res;
		}
	}

	// time O(k * n + n * log(n)), space O(n)
	public int getMinSwapsMergeSort(String num, int k) {
		int n = num.length();
		int[] nums = new int[n], target = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = num.charAt(i) - '0';
			target[i] = nums[i];
		}
		while (k-- > 0) {
			nextPermutation(target);
		}
		return minSwapMergeSort(nums, target);
	}

	private int minSwapMergeSort(int[] source, int[] target) {
		int res = 0, n = source.length;
		Deque<Integer>[] queues = new ArrayDeque[10];
		Arrays.setAll(queues, i -> new ArrayDeque<>());
		for (int i = 0; i < n; i++) {
			queues[source[i]].offer(i);
		}
		int[] idx = new int[n];
		for (int i = 0; i < n; i++) {
			idx[i] = queues[target[i]].poll();
		}
		return mergeSort(idx, 0, n - 1);
	}

	private int mergeSort(int[] nums, int left, int right) {
		if (left >= right) {
			return 0;
		}
		int mid = left + (right - left) / 2;
		int l = mergeSort(nums, left, mid);
		int r = mergeSort(nums, mid + 1, right);
		int m = merge(nums, left, right);
		return l + r + m;
	}

	private int merge(int[] nums, int left, int right) {
		int res = 0;
		int mid = left + (right - left) / 2;
		int leftIndex = left;
		int rightIndex = mid + 1;
		int countIndex = mid + 1;
		int index = 0;
		int[] temp = new int[right - left + 1];
		while (leftIndex <= mid) {
			// Counting
			while (countIndex <= right && nums[leftIndex] >= nums[countIndex]) {
				countIndex++;
			}
			res += countIndex - mid - 1;
			// Sorting
			while (rightIndex <= right && nums[rightIndex] <= nums[leftIndex]) {
				temp[index++] = nums[rightIndex++];
			}
			temp[index++] = nums[leftIndex++];
		}
		while (rightIndex <= right) {
			temp[index++] = nums[rightIndex++];
		}
		for (int i = left; i <= right; i++) {
			nums[i] = temp[i - left];
		}
		return res;
	}

	private void nextPermutation(int[] nums) {
		// #0031 next permutation
		int n = nums.length, left = n - 2;
		while (left >= 0 && nums[left] >= nums[left + 1]) {
			left--;
		}
		if (left != -1) {
			int right = n - 1, pivot = nums[left];
			while (right > left && nums[right] <= pivot) {
				right--;
			}
			nums[left] = nums[right];
			nums[right] = pivot;
		}
		for (int start = left + 1, end = n - 1; start < end; start++, end--) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
		}
	}
}
