package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 09/25/2023.
 * #2855 https://leetcode.com/problems/minimum-right-shifts-to-sort-the-array/
 */
public class MinimumRightShiftsToSortTheArray {

	// time O(n), space O(1)
	public int minimumRightShifts(List<Integer> nums) {
		int res = 0, n = nums.size(), i = 0;
		if (n == 1) { // base case
			return 0;
		}
		while (i < n - 1 && nums.get(i) < nums.get(i + 1)) { // find the pivot (if there is any), for example 5 in  [3,4,5,1,2]
			i++;
		}
		if (i++ == n - 1) { // if we reach the end, the array is already sorted
			return 0;
		}
		if (nums.get(n - 1) >= nums.get(0)) { // for the shift to work, the last value needs to be less than the first
			return -1;
		}
		while (i < n - 1 && nums.get(i) < nums.get(i + 1)) { // count the shift
			i++;
			res++;
		}
		return i == n - 1 ? res + 1 : -1; // if we can reach the end, we can sort the array by shifting.
	}
}
