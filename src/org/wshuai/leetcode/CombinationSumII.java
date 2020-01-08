package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0040 https://leetcode.com/problems/combination-sum-ii/
 */
public class CombinationSumII {
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		// sorting for pruning
		Arrays.sort(candidates);
		dfs(candidates, 0, target, new ArrayList<Integer>(), res);
		return res;
	}

	private void dfs(int[] nums, int start, int target, List<Integer> cur, List<List<Integer>> res){
		if(target <= 0){
			if(target == 0){
				res.add(new ArrayList<>(cur));
			}
			return;
		}
		for(int i = start; i < nums.length; i++){
			// pruning
			if(nums[i] > target){
				return;
			}
			// remove duplicate
			if(i > start && nums[i] == nums[i - 1]){
				continue;
			}
			cur.add(nums[i]);
			dfs(nums, i + 1, target - nums[i], cur, res);
			cur.remove(cur.size() - 1);
		}
	}
}
