package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2023.
 * #1850 https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/
 */
public class MinimumAdjacentSwapsToReachTheKthSmallestNumber {

	// time O(k * n), space O(n)
	public int getMinSwaps(String num, int k) {
		int res = 0;
		char[] arr = num.toCharArray();
		while (k-- > 0) {
			nextPermutation(arr);
		}
		return minSwaps(num.toCharArray(), arr);
	}

	private int minSwaps(char[] s1, char[] s2) {
		int res = 0, n = s1.length;
		for (int i = 0, j = 0; i < n; j = i) {
			while (s1[j] != s2[i]) {
				j++;
			}
			while (i < j) {
				char temp = s1[j];
				s1[j] = s1[j - 1];
				s1[j - 1] = temp;
				j--;
				res++;
			}
			i++;
		}
		return res;
	}

	private void nextPermutation(char[] nums) {
		int n = nums.length, left = n - 2;
		if (n <= 1) {
			return;
		}
		while (left >= 0 && nums[left] >= nums[left + 1]) {
			left--;
		}
		if (left != -1) {
			int right = n - 1;
			char pivot = nums[left];
			while (right > left && nums[right] <= pivot) {
				right--;
			}
			nums[left] = nums[right];
			nums[right] = pivot;
		}
		int start = left + 1, end = n - 1;
		while (start < end) {
			char temp = nums[end];
			nums[end--] = nums[start];
			nums[start++] = temp;
		}
	}
}
