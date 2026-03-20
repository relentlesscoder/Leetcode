package org.wshuai.leetcode.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/18/2016.
 * #0334 https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class IncreasingTripletSubsequence {

	// time O(n), space O(1)
	public boolean increasingTriplet(int[] nums) {
		int n = nums.length, m1 = Integer.MAX_VALUE, m2 = Integer.MAX_VALUE;
		for (int num : nums) {
			if (num < m1) {
				m1 = num;
			} else if (num > m1 && num < m2) {
				m2 = num;
			} else if (num > m2) {
				return true;
			}
		}
		return false;
	}

	// time O(n * log(3)), space O(1)
	public boolean increasingTripletGreedy(int[] nums) {
		// 贪心 + 二分 #0300
		int n = nums.length;
		List<Integer> arr = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int idx = binarySearch(arr, nums[i]);
			if (idx == 2) {
				return true;
			}
			if (idx == arr.size()) {
				arr.add(nums[i]);
			} else {
				arr.set(idx, nums[i]);
			}
		}
		return false;
	}

	private int binarySearch(List<Integer> arr, int target) {
		int low = 0, high = arr.size();
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (arr.get(mid) < target) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}
}
