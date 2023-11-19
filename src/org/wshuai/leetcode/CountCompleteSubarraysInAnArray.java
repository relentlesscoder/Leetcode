package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/03/2023.
 * #2799 https://leetcode.com/problems/count-complete-subarrays-in-an-array/
 */
public class CountCompleteSubarraysInAnArray {

	// time O(n), space O(1)
	public int countCompleteSubarrays(int[] nums) {
		int res = 0, n = nums.length, distinct = 0, currentCount = 0;
		int[] map = new int[2_001];
		for (int num : nums) {
			distinct += (++map[num] == 1) ? 1 : 0;
		}
		/*
		s	        e
		1	3	1	2	2

		        s		e
		1	3	1	2	2

		note that for the index 4, even we could not reach distinct unique numbers, it still can be the ending index for all substrings start between [0, s - 1] - we "inherit it from index 3
		*/
		Arrays.fill(map, 0);
		for (int start = 0, end = 0; end < n; end++) {
			currentCount += (++map[nums[end]] == 1) ? 1 : 0;
			if (currentCount == distinct) {
				while (currentCount == distinct) {
					currentCount -= (--map[nums[start++]] == 0) ? 1 : 0;
				}
			}
			res += start;
		}
		return res;
	}
}
