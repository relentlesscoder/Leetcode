package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0124 https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class BinaryTreeMaximumPathSum {
	private int res;

	// time O(n)
	public int maxPathSum(TreeNode root) {
		res = Integer.MIN_VALUE;
		dfs(root);
		return res;
	}

	private int dfs(TreeNode root){
		if(root == null){
			return 0;
		}
		int left = dfs(root.left);
		int right = dfs(root.right);
		left = left > 0 ? left : 0;
		right = right > 0 ? right : 0;
		// cross sum
		int max = root.val + left + right;
		res = Math.max(max, res);
		return Math.max(left + root.val, right + root.val);
	}
}
