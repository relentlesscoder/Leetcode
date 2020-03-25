package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/21/2019.
 * #0646 https://leetcode.com/problems/maximum-length-of-pair-chain/
 */
public class MaximumLengthOfPairChain {

	// time O(n*log(n))
	public int findLongestChain(int[][] pairs) {
		Arrays.sort(pairs, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);
		int res = 0, cur = Integer.MIN_VALUE;
		for(int[] pair : pairs){
			if(cur < pair[0]){
				cur = pair[1];
				res++;
			}
		}
		return res;
	}

	// time O(n^2), space O(n)
	public int findLongestChainDP(int[][] pairs) {
		Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
		int res = 0, n = pairs.length;
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		for(int i = 0; i < n; i++){
			for(int j = 0; j < i; j++){
				if(pairs[i][0] > pairs[j][1]){
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			res = Math.max(dp[i], res);
		}
		return res;
	}
}
