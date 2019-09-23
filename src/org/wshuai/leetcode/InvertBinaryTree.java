package org.wshuai.leetcode;

/**
 * Created by Wei on 1/23/16.
 * #226 https://leetcode.com/problems/invert-binary-tree/
 */
public class InvertBinaryTree {
	public static TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}

		if (root.left == null && root.right == null) {
			return root;
		}

		TreeNode right = root.right;
		TreeNode left = root.left;
		root.right = invertTree(left);
		root.left = invertTree(right);
		return root;
	}
}
