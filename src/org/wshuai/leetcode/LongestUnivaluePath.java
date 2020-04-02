package org.wshuai.leetcode;

/**
 * Created by Wei on 09/05/2019.
 * #0687 https://leetcode.com/problems/longest-univalue-path/
 */
public class LongestUnivaluePath {
	private int res;

	// time O(n)
	public int longestUnivaluePath(TreeNode root) {
		res = 0;
		dfs(root);
		return res;
	}

	private int dfs(TreeNode root){
		if(root == null){
			return 0;
		}
		int left = dfs(root.left);
		int right = dfs(root.right);
		left = (root.left != null && root.left.val == root.val) ? left + 1 : 1;
		right = (root.right != null && root.right.val == root.val) ? right + 1 : 1;
		// number of edges = number of nodes - 1 - 1 (double counted root node)
		res = Math.max(left + right - 2, res);
		return Math.max(left, right);
	}
}
