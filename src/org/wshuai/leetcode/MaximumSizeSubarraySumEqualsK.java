package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/02/2016.
 * #0325 https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 */
public class MaximumSizeSubarraySumEqualsK {

	// time O(n), space O(n)
	public int maxSubArrayLen(int[] nums, int k) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int res = 0, sum = 0;
		Map<Integer, Integer> prefix = new HashMap<>();
		prefix.put(0, -1);
		for(int i = 0; i < nums.length; i++){
			sum += nums[i];
			if(prefix.containsKey(sum - k)){
				res = Math.max(res, i - prefix.get(sum - k));
			}
			prefix.putIfAbsent(sum, i);
		}
		return res;
	}
}
