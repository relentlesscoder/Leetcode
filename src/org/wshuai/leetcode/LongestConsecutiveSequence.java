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
		if(nums == null || nums.length == 0){
			return 0;
		}
		int res = 0;
		Set<Integer> set = new HashSet<>();
		for(int num : nums){
			set.add(num);
		}
		for(int i = 0; i < nums.length; i++){
			if(!set.contains(nums[i])){
				continue;
			}
			int len = 1, left = nums[i], right = nums[i];
			while(set.contains(--left)){
				len++;
				set.remove(left);
			}
			while(set.contains(++right)){
				len++;
				set.remove(right);
			}
			set.remove(nums[i]);
			res = Math.max(res, len);
		}
		return res;
	}
}
