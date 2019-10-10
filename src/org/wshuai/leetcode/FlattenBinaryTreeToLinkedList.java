package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #114 https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class FlattenBinaryTreeToLinkedList {
	public void flatten(TreeNode root) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			return;
		}
		flatten(root.left);
		flatten(root.right);
		TreeNode node = root.right;
		if (root.left != null) {
			root.right = root.left;
			root.left = null;
			while (root.right != null) {
				root = root.right;
			}
		}
		if (node != null) {
			root.right = node;
		}
	}
}
