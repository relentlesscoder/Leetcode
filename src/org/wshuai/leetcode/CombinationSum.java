package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0039 https://leetcode.com/problems/combination-sum/
 */
public class CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
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
			// pruning the current branch because the array is sorted
			if(nums[i] > target){
				return;
			}
			cur.add(nums[i]);
			dfs(nums, i, target - nums[i], cur, res);
			cur.remove(cur.size() - 1);
		}
	}
}
