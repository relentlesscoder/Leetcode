package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/02/2016.
 * #0325 https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 */
public class MaximumSizeSubarraySumEqualsK {
	//O(n) time, same idea as #303 https://leetcode.com/problems/range-sum-query-immutable/
	//but use hashmap to improve the time
	public int maxSubArrayLen(int[] nums, int k) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int n = nums.length, res = 0;
		for(int i = 1; i < n; i++){
			nums[i] += nums[i - 1];
		}
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		for(int i = 0; i < n; i++){
			if(map.containsKey(nums[i] - k)){
				res = Math.max(res, i - map.get(nums[i] - k));
			}
			if(!map.containsKey(nums[i])){
				map.put(nums[i], i);
			}
		}
		return res;
	}
}
