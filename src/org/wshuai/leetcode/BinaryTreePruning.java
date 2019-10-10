package org.wshuai.leetcode;

/**
 * Created by Wei on 8/31/2019.
 * #814 https://leetcode.com/problems/binary-tree-pruning/
 */
public class BinaryTreePruning {
	public TreeNode pruneTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		root.left = pruneTree(root.left);
		root.right = pruneTree(root.right);
		if (root.val == 0 && root.left == null && root.right == null) {
			root = null;
		}
		return root;
	}
}
