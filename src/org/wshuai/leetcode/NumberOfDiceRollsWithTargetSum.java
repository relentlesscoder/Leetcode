package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/16/2019.
 * #1155 https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 */
public class NumberOfDiceRollsWithTargetSum {
	private final int MOD = 1_000_000_007;
	private Map<Integer, Integer> map;

	public int numRollsToTarget(int d, int f, int target) {
		map = new HashMap<>();
		return dfs(0, d, f, target);
	}

	private int dfs(int i, int d, int f, int t){
		int key = t * 1000 + i;
		if(!map.containsKey(key)){
			int res = 0;
			if(i == d){
				return t == 0 ? 1 : 0;
			}
			for(int x = 1; x <= f; x++){
				res = (res + dfs(i + 1, d, f, t - x)) % MOD;
			}
			map.put(key, res);
		}
		return map.get(key);
	}
}
