package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/22/19.
 * #1259 https://leetcode.com/problems/handshakes-that-dont-cross/
 */
public class HandshakesThatDontCross {
	private final int mod = 1_000_000_007;
	private Map<Integer, Integer> dp;

	public int numberOfWays(int num_people) {
		dp = new HashMap<>();
		return dfs(num_people);
	}

	private int dfs(int n){
		if(dp.containsKey(n)){
			return dp.get(n);
		}
		if(n % 2 == 1){
			return 0;
		}
		if(n == 0){
			return 1;
		}
		long sum = 0;
		for(int i = 0; i < n; i += 2){
			sum += ((long)dfs(i)) * dfs(n - 2 - i);
			sum %= mod;
		}
		dp.put(n, (int)sum);
		return dp.get(n);
	}
}
