package org.wshuai.leetcode;

/**
 * Created by Wei on 1/23/16.
 * #110 https://leetcode.com/problems/balanced-binary-tree/
 */
public class BalancedBinaryTree {
	public static boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		return isBalanced(root.left) && isBalanced(root.right) &&
				Math.abs(MaximumDepthOfBinaryTree.maxDepthRecursive(root.left)
						- MaximumDepthOfBinaryTree.maxDepthRecursive(root.right)) <= 1;
	}
}
