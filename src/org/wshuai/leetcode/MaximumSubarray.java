package org.wshuai.leetcode;

/**
 * Created by Wei on 10/12/2016.
 * #53 https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubarray {

	// O(n)
	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int max = nums[0];
		int curr = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int x = curr + nums[i];
			curr = x > nums[i] ? x : nums[i];
			max = curr > max ? curr : max;
		}
		return max;
	}

	// O(nlog(n)) divide and conquer
	public int maxSubArrayDC(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int i = 0;
		int j = nums.length - 1;
		return maxSubArrayDCUtil(nums, i, j);
	}

	private int maxSubArrayDCUtil(int[] nums, int i, int j) {
		if (i == j) {
			return nums[i];
		} else {
			int m = i + (j - i) / 2;
			int left = maxSubArrayDCUtil(nums, i, m);
			int right = maxSubArrayDCUtil(nums, m + 1, j);
			int mid = getMiddleMaxSubArray(nums, m, i, j);
			int max = left > right ? left : right;
			max = mid > max ? mid : max;
			return max;
		}
	}

	private int getMiddleMaxSubArray(int[] nums, int mid, int i, int j) {
		int sum = 0;
		int left = Integer.MIN_VALUE;
		int k = mid;
		while (k >= i) {
			sum += nums[k];
			left = sum > left ? sum : left;
			k--;
		}
		sum = 0;
		int right = Integer.MIN_VALUE;
		k = mid + 1;
		while (k <= j) {
			sum += nums[k];
			right = sum > right ? sum : right;
			k++;
		}
		return left + right;
	}
}
