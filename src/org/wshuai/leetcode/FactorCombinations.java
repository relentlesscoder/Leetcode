package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/01/2016.
 * #0254 https://leetcode.com/problems/factor-combinations/
 */
public class FactorCombinations {
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> res = new ArrayList<>();
		dfs(n, 2, new ArrayList<Integer>(), res);
		return res;
	}

	private void dfs(int n, int start, List<Integer> cur, List<List<Integer>> res){
		if(n <= 1){
			if(cur.size() > 1){
				res.add(new ArrayList<>(cur));
			}
			return;
		}
		for(int i = start; i <= Math.sqrt(n); i++){
			if(n % i != 0){
				continue;
			}
			cur.add(i);
			// start from i since the next factor will not be less than i,
			// if it n / i can be divided by some factor less than i then n
			// can also be divided by it.
			dfs(n / i, i, cur, res);
			cur.remove(cur.size() - 1);
		}
		// force to add the last factor
		cur.add(n);
		dfs(1, n, cur, res);
		cur.remove(cur.size() - 1);
	}
}
