package org.wshuai.leetcode;

/**
 * Created by Wei on 09/05/2019.
 * #1026 https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
 */
public class MaximumDifferenceBetweenNodeAndAncestor {

	private int res;

	// time O(n)
	public int maxAncestorDiff(TreeNode root) {
		res = Integer.MIN_VALUE;
		dfs(root.left, root.val, root.val);
		dfs(root.right, root.val, root.val);
		return res;
	}

	private void dfs(TreeNode node, int max, int min){
		if(node == null){
			return;
		}
		max = Math.max(max, node.val);
		min = Math.min(min, node.val);
		res = Math.max(res, max - min);
		dfs(node.left, max, min);
		dfs(node.right, max, min);
	}
}
