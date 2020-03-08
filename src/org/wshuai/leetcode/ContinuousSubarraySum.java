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
		Map<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		map.put(0, -1);
		for(int i = 0; i < nums.length; i++){
			sum += nums[i];
			if(k != 0){
				sum %= k;
			}
			if(map.containsKey(sum)){
				if(i - map.get(sum) > 1){
					return true;
				}
			}else{
				map.put(sum, i);
			}
		}
		return false;
	}
}
