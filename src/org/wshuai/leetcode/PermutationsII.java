package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/17/2016.
 * #0047 https://leetcode.com/problems/permutations-ii/
 */
public class PermutationsII {
	// time O(n!)
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		dfs(nums, new ArrayList<>(), new boolean[nums.length], res);
		return res;
	}

	private void dfs(int[] nums, List<Integer> cur, boolean[] used, List<List<Integer>> res){
		if(cur.size() == nums.length){
			res.add(new ArrayList<>(cur));
			return;
		}
		for(int i = 0; i < nums.length; i++){
			if(used[i] || (i > 0 && nums[i] == nums[i - 1] && used[i - 1])){
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
