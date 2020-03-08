package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/29/2016.
 * #0437 https://leetcode.com/problems/path-sum-iii/
 */
public class PathSumIII {
	// time O(n), space O(n)
	public int pathSum(TreeNode root, int sum) {
		Map<Integer, Integer> prefix = new HashMap<>();
		prefix.put(0, 1);
		return dfs(root, 0, sum, prefix);
	}

	private int dfs(TreeNode root, int sum, int target, Map<Integer, Integer> prefix){
		if(root == null){
			return 0;
		}
		sum += root.val;
		int res = prefix.getOrDefault(sum - target, 0);
		prefix.put(sum, prefix.getOrDefault(sum, 0) + 1);

		res += dfs(root.left, sum, target, prefix) + dfs(root.right, sum, target, prefix);
		// for binary tree, we need to delete the current prefix sum
		// since other paths cannot use it.
		prefix.put(sum, prefix.get(sum) - 1);
		return res;
	}

	// time O(n^2)
	public int pathSumRecursive(TreeNode root, int sum) {
		if(root == null){
			return 0;
		}
		return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
	}

	private int pathSumFrom(TreeNode root, int sum){
		if(root == null){
			return 0;
		}
		return (root.val == sum ? 1 : 0) + pathSumFrom(root.left, sum - root.val) + pathSumFrom(root.right, sum - root.val);
	}
}