package org.wshuai.leetcode;

import java.util.TreeSet;

/**
 * Created by Wei on 10/10/2016.
 * #0220 https://leetcode.com/problems/contains-duplicate-iii/
 */
public class ContainsDuplicateIII {
	// time O(n*log(n))
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if(nums == null || nums.length == 0){
			return false;
		}
		int n = nums.length;
		TreeSet<Long> set = new TreeSet<>();
		for(int i = 0; i < n; i++){
			long val = (long)nums[i];
			Long floor = set.floor(val + t);
			if(floor != null && floor >= val - t){
				return true;
			}
			set.add(val);
			if(set.size() > k){
				set.remove((long)nums[i - k]);
			}
		}
		return false;
	}
}
