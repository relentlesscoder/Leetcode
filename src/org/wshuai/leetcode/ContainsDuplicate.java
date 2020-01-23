package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/10/2016.
 * #0217 https://leetcode.com/problems/contains-duplicate/
 */
public class ContainsDuplicate {
	// time O(n), space O(n)
	public boolean containsDuplicate(int[] nums) {
		if(nums == null || nums.length < 2){
			return false;
		}
		Set<Integer> set = new HashSet<>();
		for(int i : nums){
			if(!set.add(i)){
				return true;
			}
		}
		return false;
	}
}
