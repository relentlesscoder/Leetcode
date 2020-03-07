package org.wshuai.leetcode;

/**
 * Created by Wei on 04/03/2017.
 * #0543 https://leetcode.com/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTree {
	private int diameter;

	// time O(n)
	public int diameterOfBinaryTree(TreeNode root) {
		diameter = 0;
		if(root == null){
			return 0;
		}
		dfs(root);
		return diameter;
	}

	private int dfs(TreeNode root){
		if(root == null){
			return 0;
		}
		int left = root.left == null ? 0 : 1 + dfs(root.left);
		int right = root.right == null ? 0 : 1 + dfs(root.right);
		int res = Math.max(left, right);
		diameter = Math.max(left + right, diameter);
		return res;
	}
}
