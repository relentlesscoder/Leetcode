package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 02/27/2017.
 * #0525 https://leetcode.com/problems/contiguous-array/
 */
public class ContiguousArray {
	// time O(n), space O(n)
	public int findMaxLength(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int len = nums.length;
		for(int i = 0; i < len; i++){
			if(nums[i] == 0){
				nums[i] = -1;
			}
		}
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		int max = 0, sum = 0;
		for(int i = 0; i < len; i++){
			sum += nums[i];
			if(map.containsKey(sum)){
				int idx = map.get(sum);
				max = Math.max(max, i - idx);
			}else{
				map.put(sum, i);
			}
		}
		return max;
	}
}
