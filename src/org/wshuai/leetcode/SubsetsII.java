package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/05/2016.
 * #0090 https://leetcode.com/problems/subsets-ii/
 */
public class SubsetsII {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		dfs(nums, 0, new ArrayList<Integer>(), res);
		return res;
	}

	private void dfs(int[] nums, int start, List<Integer> cur, List<List<Integer>> res){
		res.add(new ArrayList<>(cur));
		for(int i = start; i < nums.length; i++){
			if(i > start && nums[i] == nums[i - 1]){
				continue;
			}
			cur.add(nums[i]);
			dfs(nums, i + 1, cur, res);
			cur.remove(cur.size() - 1);
		}
	}
}
