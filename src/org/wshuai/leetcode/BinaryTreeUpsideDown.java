package org.wshuai.leetcode;

/**
 * Created by Wei on 10/2/2016.
 */
public class BinaryTreeUpsideDown {
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		TreeNode node = root;
		TreeNode parent = null;
		TreeNode right = null;

		while (node != null) {
			TreeNode left = node.left;
			node.left = right;
			right = node.right;
			node.right = parent;
			parent = node;
			node = left;
		}

		return parent;
	}
}
