package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/07/2016.
 * #0397 https://leetcode.com/problems/integer-replacement/
 */
public class IntegerReplacement {
	// time O(log(n)), space O(n)
	public int integerReplacementRecursionWithMemo(int n) {
		Map<Integer, Integer> map = new HashMap<>();
		return dfs(n, map);
	}

	private int dfs(int n, Map<Integer, Integer> map){
		if(n <= 1){
			return 0;
		}
		if(!map.containsKey(n)){
			if(n % 2 == 0){
				map.put(n, 1 + dfs(n / 2, map));
			}else{
				map.put(n, 2 + Math.min(dfs(n / 2, map), dfs(n / 2 + 1, map)));
			}
		}
		return map.get(n);
	}

	// MLE
	public int integerReplacementDP(int n) {
		int[] dp = new int[n + 1];
		for(int i = 2; i <= n; i++){
			if(i % 2 == 0){
				dp[i] = dp[i / 2] + 1;
			}else{
				dp[i] = Math.min(dp[i / 2], dp[i / 2 + 1]) + 2;
			}
		}
		return dp[n];
	}
}
