package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0039 https://leetcode.com/problems/combination-sum/
 */
public class CombinationSum {

	// time O(k*2^n), space O(n)
	// complexity analysis https://github.com/Deadbeef-ECE/Interview/blob/master/Leetcode/BackTracking/039_Combination_Sum.java
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
			// pruning the current branch because the array is sorted
			if(candidates[i] > target){
				break;
			}
			cur.add(candidates[i]);
			dfs(i, target - candidates[i], candidates, cur, res);
			cur.remove(cur.size() - 1);
		}
	}
}
