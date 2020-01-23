package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #0216 https://leetcode.com/problems/combination-sum-iii/
 */
public class CombinationSumIII {
	// time O(9!/(9 - k)!)
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> res = new ArrayList<>();
		int[] nums = new int[9];
		for(int i = 0; i < 9; i++){
			nums[i] = i + 1;
		}
		dfs(0, nums, k, n, new ArrayList<>(), res);
		return res;
	}

	private void dfs(int start, int[] nums, int k, int n, List<Integer> cur, List<List<Integer>> res){
		if(k == 0){
			if(n == 0){
				res.add(new ArrayList<>(cur));
			}
			return;
		}
		if(n < 0){
			return;
		}
		for(int i = start; i < nums.length; i++){
			cur.add(nums[i]);
			dfs(i + 1, nums, k - 1, n - nums[i], cur, res);
			cur.remove(cur.size() - 1);
		}
	}
}
