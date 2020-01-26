package org.wshuai.leetcode;

/**
 * Created by Wei on 01/23/2016.
 * #0226 https://leetcode.com/problems/invert-binary-tree/
 */
public class InvertBinaryTree {
	// time O(n)
	public TreeNode invertTree(TreeNode root) {
		if(root == null){
			return null;
		}
		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		root.left = right;
		root.right = left;
		return root;
	}
}
