package org.wshuai.leetcode;

/**
 * Created by Wei on 8/9/19.
 * #897 https://leetcode.com/problems/increasing-order-search-tree/
 */
public class IncreasingOrderSearchTree {
	private TreeNode curr;

	public TreeNode increasingBST(TreeNode root) {
		TreeNode res = new TreeNode(0);
		curr = res;
		inOrder(root);
		return res.right;
	}

	private void inOrder(TreeNode node) {
		if (node == null) {
			return;
		}
		inOrder(node.left);
		node.left = null;
		curr.right = node;
		curr = node;
		inOrder(node.right);
	}
}
