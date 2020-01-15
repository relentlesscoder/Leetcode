package org.wshuai.leetcode;

/**
 * Created by Wei on 01/23/2016.
 * #0110 https://leetcode.com/problems/balanced-binary-tree/
 */
public class BalancedBinaryTree {
	// time O(n)
	public boolean isBalanced(TreeNode root) {
		if(root == null){
			return true;
		}
		int left = depth(root.left);
		int right = depth(root.right);
		return Math.abs(left - right) <= 1
				&& isBalanced(root.left)
				&& isBalanced(root.right);
	}

	private int depth(TreeNode root){
		if(root == null){
			return 0;
		}
		return 1 + Math.max(depth(root.left), depth(root.right));
	}
}
