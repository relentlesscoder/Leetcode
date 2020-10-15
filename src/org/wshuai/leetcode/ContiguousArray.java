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
		int res = 0, n = nums.length, sum = 0;
		for(int i = 0; i < n; i++){
			nums[i] = nums[i] == 0 ? -1 : 1;
		}
		Map<Integer, Integer> prefix = new HashMap<>();
		prefix.put(0, -1);
		for(int i = 0; i < n; i++){
			sum += nums[i];
			if(prefix.containsKey(sum)){
				res = Math.max(res, i - prefix.get(sum));
			}else{
				prefix.put(sum, i);
			}
		}
		return res;
	}
}
