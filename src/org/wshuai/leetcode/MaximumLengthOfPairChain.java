package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/21/19.
 * #646 https://leetcode.com/problems/maximum-length-of-pair-chain/
 */
public class MaximumLengthOfPairChain {

	// DP
	public int findLongestChain(int[][] pairs) {
		Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
		int N = pairs.length;
		int[] dp = new int[N];
		Arrays.fill(dp, 1);

		for(int j = 1; j < N; j++){
			for(int i = 0; i < j; i++){
				if(pairs[i][1] < pairs[j][0]){
					dp[j] = Math.max(dp[j], dp[i] + 1);
				}
			}
		}

		int res = 0;
		for(int d: dp){
			res = Math.max(d, res);
		}
		return res;
	}

	Map<Integer, Integer> map;

	public int findLongestChainMemorization(int[][] pairs) {
		map = new HashMap<>();
		int max = 0;
		for(int i = 0; i < pairs.length; i++){
			max = Math.max(max, dfs(i, pairs));
		}
		return max;
	}

	private int dfs(int i, int[][] p){
		int max = 0;
		int[] c = p[i];
		int key = c[0] * 1000 + c[1];
		if(map.containsKey(key)){
			return map.get(key);
		}
		for(int j = 0; j < p.length; j++){
			if(j == i || c[1] >= p[j][0]){
				continue;
			}
			max = Math.max(max, dfs(j, p));
		}
		map.put(key, max + 1);
		return map.get(key);
	}
}
