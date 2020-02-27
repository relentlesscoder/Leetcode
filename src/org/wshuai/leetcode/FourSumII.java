package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/18/2016.
 * #0454 https://leetcode.com/problems/4sum-ii/
 */
public class FourSumII {
	// time O(N^2), space O(N^2)
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		int res = 0, n = A.length;
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				int sum = A[i] + B[j];
				map.put(-sum, map.getOrDefault(-sum, 0) + 1);
			}
		}
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				int sum = C[i] + D[j];
				res += map.getOrDefault(sum, 0);
			}
		}
		return res;
	}
}
