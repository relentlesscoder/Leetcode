package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/17/2016.
 * #0047 https://leetcode.com/problems/permutations-ii/
 */
public class PermutationsII {

	// time O(n!)
	public List<List<Integer>> permuteUnique(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), res);
		return res;
	}

	private void dfs(int[] nums, boolean[] used, List<Integer> cur, List<List<Integer>> res){
		if(cur.size() == nums.length){
			res.add(new ArrayList<Integer>(cur));
			return;
		}
		for(int i = 0; i < nums.length; i++){
			if(used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])){
				continue;
			}
			used[i] = true;
			cur.add(nums[i]);
			dfs(nums, used, cur, res);
			cur.remove(cur.size() - 1);
			used[i] = false;
		}
	}
}
