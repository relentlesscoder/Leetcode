package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0077 https://leetcode.com/problems/combinations/
 */
public class Combinations {
	// time O(n!/(n-k)!)
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		if(k > n){
			return res;
		}
		dfs(new ArrayList<Integer>(), 1, k, n, res);
		return res;
	}

	private void dfs(List<Integer> cur, int start, int k, int n, List<List<Integer>> res){
		if(k == 0){
			res.add(new ArrayList<>(cur));
			return;
		}
		for(int i = start; i <= n; i++){
			cur.add(i);
			dfs(cur, i + 1, k - 1, n, res);
			cur.remove(cur.size() - 1);
		}
	}
}
