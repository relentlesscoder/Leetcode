package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/21/2016.
 * #128 https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutiveSequence {
	public int longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int len = nums.length;
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < len; i++) {
			set.add(nums[i]);
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			int val = nums[i];
			if (set.contains(val)) {
				int cLen = 1;
				int right = val + 1;
				while (set.contains(right)) {
					cLen++;
					set.remove(right);
					right++;
				}
				int left = val - 1;
				while (set.contains(left)) {
					cLen++;
					set.remove(left);
					left--;
				}
				set.remove(val);
				max = cLen > max ? cLen : max;
			}
		}

		return max;
	}
}
