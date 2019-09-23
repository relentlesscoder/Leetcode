package org.wshuai.leetcode;

/**
 * Created by Wei on 3/7/17.
 * #530 https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 */
public class MinimumAbsoluteDifferenceInBST {
	//In order traversal of a BST is sorted
	int minDiff = Integer.MAX_VALUE;
	TreeNode prev = null;

	public int getMinimumDifference(TreeNode root) {
		inOrder(root);
		return minDiff;
	}

	private void inOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		inOrder(root.left);
		if (prev != null) {
			minDiff = Math.min(minDiff, Math.abs(root.val - prev.val));
		}
		prev = root;
		inOrder(root.right);
	}
}
