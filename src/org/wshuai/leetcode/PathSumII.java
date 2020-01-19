package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/29/2016.
 * #0113 https://leetcode.com/problems/path-sum-ii/
 */
public class PathSumII {
	// time O(n)
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<>();
		dfs(root, new ArrayList<>(), sum, res);
		return res;
	}

	private void dfs(TreeNode node, List<Integer> cur, int sum, List<List<Integer>> res){
		if(node == null){
			return;
		}
		cur.add(node.val);
		if(node.left == null && node.right == null && sum == node.val){
			res.add(new ArrayList<>(cur));
		}
		dfs(node.left, cur, sum - node.val, res);
		dfs(node.right, cur, sum - node.val, res);
		cur.remove(cur.size() - 1);
	}
}
