package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/21/2016.
 * #0128 https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutiveSequence {
	// time O(n), space O(n)
	public int longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int n = nums.length;
		Set<Integer> set = new HashSet<Integer>();
		for (int num : nums) {
			set.add(num);
		}
		for (int i = 0; i < n; i++) {
			if (set.contains(nums[i])) {
				int val = nums[i], cLen = 1, right = val, left = val;
				while (set.contains(++right)) {
					cLen++;
					set.remove(right);
				}
				while (set.contains(--left)) {
					cLen++;
					set.remove(left);
				}
				set.remove(val);
				max = cLen > max ? cLen : max;
			}
		}
		return max;
	}
}
