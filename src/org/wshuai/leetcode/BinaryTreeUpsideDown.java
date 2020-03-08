package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2016.
 * #0156 https://leetcode.com/problems/binary-tree-upside-down/
 */
public class BinaryTreeUpsideDown {
	private TreeNode res;

	// time O(n)
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		res = null;
		if(root == null){
			return res;
		}
		dfs(root);
		return res;
	}

	private TreeNode dfs(TreeNode root){
		if(root.left == null){
			res = root;
			return root;
		}
		TreeNode left  = dfs(root.left);
		TreeNode right = root.right;
		root.left = null;
		root.right = null;
		left.left = right;
		left.right = root;
		return root;
	}
}
