package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/05/2019.
 * #0560 https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SubarraySumEqualsK {

	// time O(n), space O(n)
	public int subarraySum(int[] nums, int k) {
		int res = 0, n = nums.length, sum = 0;
		Map<Integer, Integer> prefix = new HashMap<>();
		prefix.put(0, 1);
		for(int i = 0; i < n; i++){
			sum += nums[i];
			if(prefix.containsKey(sum - k)){
				res += prefix.get(sum - k);
			}
			prefix.put(sum, prefix.getOrDefault(sum, 0) + 1);
		}
		return res;
	}

}
