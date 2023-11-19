package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/27/2019.
 * #0974 https://leetcode.com/problems/subarray-sums-divisible-by-k/
 */
public class SubarraySumsDivisibleByK {

	// time O(n), space O(n)
	// see https://leetcode.com/problems/subarray-sums-divisible-by-k/discuss/217980/Java-O(N)-with-HashMap-and-prefix-Sum
	public int subarraysDivByK(int[] A, int K) {
		int res = 0, n = A.length, sum = 0;
		Map<Integer, Integer> prefix = new HashMap<>();
		prefix.put(0, 1);
		for(int i = 0; i < A.length; i++){
			sum = (sum + A[i]) % K;
			sum += sum < 0 ? K : 0;
			res += prefix.getOrDefault(sum, 0);
			prefix.put(sum, prefix.getOrDefault(sum, 0) + 1);
		}
		return res;
	}
}
