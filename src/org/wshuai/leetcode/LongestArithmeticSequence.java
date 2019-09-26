package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 9/25/19.
 * #1027 https://leetcode.com/problems/longest-arithmetic-sequence/
 */
public class LongestArithmeticSequence {
	public int longestArithSeqLength(int[] A) {
		int res = 2;
		int len = A.length;
		Map<Integer, Integer>[] dp = new HashMap[len];
		// dp[index][diff] equals to the length of arithmetic sequence at index with difference diff.
		// 0:
		// 1: 3 - {2}
		// 2: 6 - {2}, 3 - { 2 + 1 = 3} (dp[1].get(3) = 2)
		// 3: 9 - {2}, 6 - {2}, 3 - {4}
		for(int j = 0; j < A.length; j++){
			dp[j] = new HashMap<>();
			for(int i = 0; i < j; i++){
				int d = A[j] - A[i];
				dp[j].put(d, dp[i].getOrDefault(d, 1) + 1);
				res = Math.max(res, dp[j].get(d));
			}
		}
		return res;
	}
}
