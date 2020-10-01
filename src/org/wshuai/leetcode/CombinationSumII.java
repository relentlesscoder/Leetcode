package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0040 https://leetcode.com/problems/combination-sum-ii/
 */
public class CombinationSumII {

	// time O(2^n)
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> res = new ArrayList<>();
		dfs(0, target, candidates, new ArrayList<Integer>(), res);
		return res;
	}

	private void dfs(int start, int target, int[] candidates, List<Integer> cur, List<List<Integer>> res){
		if(target == 0){
			res.add(new ArrayList<>(cur));
		}
		for(int i = start; i < candidates.length; i++){
			if(candidates[i] > target){
				break;
			}
			if(i > start && candidates[i] == candidates[i - 1]){
				continue;
			}
			cur.add(candidates[i]);
			dfs(i + 1, target - candidates[i], candidates, cur, res);
			cur.remove(cur.size() - 1);
		}
	}
}
