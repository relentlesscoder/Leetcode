package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2023.
 * #2210 https://leetcode.com/problems/count-hills-and-valleys-in-an-array/
 */
public class CountHillsAndValleysInAnArray {

	// time O(n), space O(1)
	public int countHillValley(int[] nums) {
		int res = 0, n = nums.length;
		for (int left = 0, middle = 1; middle < n - 1; middle++) {
			// fix the left and advance middle until we find a hill or valley, duplicate will be skipped during the walk
			// for example [2,4,1,1,6,5],
			// L -> 0, M -> 1    2,4,1 hill
			// L -> 1, M -> 2    4,1,1 invalid
			// L -> 1, M -> 3    4,1,6 valley
			// L -> 3, M -> 4    1,6,5 hill
			// L -> 4, M -> 5    6,5    invalid
			if ((nums[left] > nums[middle] && nums[middle] > nums[middle + 1]) ||
					(nums[left] < nums[middle] && nums[middle] < nums[middle + 1])) {
				res++;
				left = middle;
			}
		}
		return res;
	}

	// time O(n), space O(1)
	public int countHillValleyStack(int[] nums) {
		int res = 0, n = nums.length, lastOne = -1, lastTwo = -1; // we could use stack to record all previous values but since we only care about 3 values we can record previous two values to save the space
		for (int i = 0; i < n; i++) {
			if (lastOne == nums[i]) { // skip the duplicates
				continue;
			}
			if (lastOne != -1 && lastTwo != -1) {
				if ((lastOne > lastTwo && lastOne > nums[i])
						|| (lastOne < lastTwo && lastOne < nums[i])) { // add hill or valley
					res++;
				}
			}
			lastTwo = lastOne;
			lastOne = nums[i];
		}
		return res;
	}
}
