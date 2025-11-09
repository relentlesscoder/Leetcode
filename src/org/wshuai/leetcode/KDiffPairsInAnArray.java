package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 03/05/2017.
 * #0532 https://leetcode.com/problems/k-diff-pairs-in-an-array/
 */
public class KDiffPairsInAnArray {

	// time O(n * log(n)), space O(1)
	public int findPairs(int[] nums, int k) {
		int res = 0, n = nums.length;
		Arrays.sort(nums);
		for (int i = 0; i < n; i++) {
			// Note it's incorrect to check nums[i] == nums[i + 1] for case like
			// nums: [1,1,2,3,4] k: 0
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int index = binarySearch(nums, i + 1, n - 1, nums[i] + k);
			if (index != -1) {
				res++;
			}
		}
		return res;
	}

	private int binarySearch(int[] nums, int low, int high, int target) {
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] < target) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low < nums.length && nums[low] == target ? low : -1;
	}

	// time O(n), space O(n)
	public int findPairsHashSet(int[] nums, int k) {
		int res = 0;
		Arrays.sort(nums);
		Set<Integer> visited = new HashSet<>(), used = new HashSet<>();
		for(int i = 0; i < nums.length; i++){
			if(visited.contains(nums[i] - k) && !used.contains(nums[i])){
				used.add(nums[i]);
				res++;
			}
			visited.add(nums[i]);
		}
		return res;
	}
}
