package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/20/2020.
 * #1590 https://leetcode.com/problems/make-sum-divisible-by-p/
 */
public class MakeSumDivisibleByP {

	public int minSubarray(int[] nums, int p) {
		Map<Integer, Integer> map = new HashMap<>();
		int n = nums.length, res = n, target = 0, sum = 0;
		for(int i = 0; i < n; i++){
			target = (target + nums[i]) % p;
		}
		if(target % p == 0){
			return 0;
		}
		map.put(0, -1);
		for(int i = 0; i < n; i++){
			sum = (sum + nums[i]) % p;
			int key = (sum - target + p) % p;
			if(map.containsKey(key)){
				res = Math.min(res, i - map.get(key));
			}
			map.put(sum, i);
		}
		return res == n ? -1 : res;
	}
}
