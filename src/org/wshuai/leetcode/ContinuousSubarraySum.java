package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 03/05/2017.
 * #0523 https://leetcode.com/problems/continuous-subarray-sum/
 */
public class ContinuousSubarraySum {
	
	// time O(n), space O(n)
	public boolean checkSubarraySum(int[] nums, int k) {
		int n = nums.length, sum = 0;
		Map<Integer, Integer> prefix = new HashMap<>();
		prefix.put(0, -1);
		for(int i = 0; i < n; i++){
			sum += nums[i];
			if(k != 0){ // special case for 0 needs two equal sum
				sum %= k;
			}
			if(prefix.containsKey(sum) && i - prefix.get(sum) > 1){
				return true;
			}
			prefix.putIfAbsent(sum, i);
		}
		return false;
	}
}
