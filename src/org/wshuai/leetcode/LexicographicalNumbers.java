package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/19/2016.
 * #0386 https://leetcode.com/problems/lexicographical-numbers/
 */
public class LexicographicalNumbers {
	// time O(10^d)
	public List<Integer> lexicalOrder(int n) {
		List<Integer> res = new ArrayList<>();
		for(int i = 1; i <= 9; i++){
			dfs(i, n, res);
		}
		return res;
	}

	private void dfs(int cur, int n, List<Integer> res){
		if(cur > n){
			return;
		}
		res.add(cur);
		for(int i = 0; i < 10; i++){
			dfs(cur * 10 + i, n, res);
		}
	}
}
