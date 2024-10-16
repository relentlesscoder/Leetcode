package org.wshuai.leetcode;

/**
 * Created by Wei on 04/03/2017.
 * #0543 https://leetcode.com/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTree {

	private int diameter = 0;

	public int diameterOfBinaryTree(TreeNode root) {
		dfs(root);
		return diameter;
	}

	private int dfs(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = dfs(root.left);
		int right = dfs(root.right);
		diameter = Math.max(diameter, left + right);
		return Math.max(left, right) + 1;
	}
}
