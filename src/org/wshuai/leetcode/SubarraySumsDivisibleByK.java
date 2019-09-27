package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 9/27/19.
 * #974 https://leetcode.com/problems/subarray-sums-divisible-by-k/
 */
public class SubarraySumsDivisibleByK {

	// see https://leetcode.com/problems/subarray-sums-divisible-by-k/discuss/217980/Java-O(N)-with-HashMap-and-prefix-Sum
	public int subarraysDivByK(int[] A, int K) {
		Map<Integer, Integer> map = new HashMap<>();
		// we need two equal modulo to form a subarray
		// modulo = 0 is the only exception so need to add 1
		map.put(0, 1);
		int count = 0;
		int sum = 0;
		for(int a: A){
			sum = (sum + a) % K;
			// java reminder can be negative
			// see https://stackoverflow.com/questions/5385024/mod-in-java-produces-negative-numbers
			if(sum < 0){
				sum += K;
			}
			count += map.getOrDefault(sum, 0);
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}
}
