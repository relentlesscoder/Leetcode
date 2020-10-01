package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/05/2016.
 * #0090 https://leetcode.com/problems/subsets-ii/
 */
public class SubsetsII {

	// time O(2^n)
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		dfs(0, nums, new ArrayList<Integer>(), res);
		return res;
	}

	private void dfs(int start, int[] nums, List<Integer> cur, List<List<Integer>> res){
		res.add(new ArrayList<>(cur));
		for(int i = start; i < nums.length; i++){
			if(i > start && nums[i] == nums[i - 1]){
				continue;
			}
			cur.add(nums[i]);
			dfs(i + 1, nums, cur, res);
			cur.remove(cur.size() - 1);
		}
	}
}
