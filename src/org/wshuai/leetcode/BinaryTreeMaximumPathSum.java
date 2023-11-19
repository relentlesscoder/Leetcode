package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0124 https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class BinaryTreeMaximumPathSum {

	private int max;

	// time O(n)
	public int maxPathSum(TreeNode root) {
		max = Integer.MIN_VALUE;
		dfs(root);
		return max;
	}

	private int dfs(TreeNode root){
		if(root == null){
			return 0;
		}
		int left = Math.max(0, dfs(root.left));
		int right = Math.max(0, dfs(root.right));
		// max sum cross the current node
		max = Math.max(max, root.val + left + right);
		// return larger sum for parent to form a cross path sum
		return Math.max(left, right) + root.val;
	}
}
