package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/11/2016.
 * #0046 https://leetcode.com/problems/permutations/
 */
public class Permutations {
	// time O(n!)
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		dfs(nums, new ArrayList<>(), new boolean[nums.length], res);
		return res;
	}

	private void dfs(int[] nums, List<Integer> cur, boolean[] used, List<List<Integer>> res){
		if(cur.size() == nums.length){
			res.add(new ArrayList<>(cur));
			return;
		}
		for(int i = 0; i < nums.length; i++){
			if(used[i]){
				continue;
			}
			cur.add(nums[i]);
			used[i] = true;
			dfs(nums, cur, used, res);
			used[i] = false;
			cur.remove(cur.size() - 1);
		}
	}
}
