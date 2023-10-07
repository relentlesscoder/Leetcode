package org.wshuai.leetcode;

/**
 * Created by Wei on 11/09/2020.
 * #1644 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
 */
public class LowestCommonAncestorOfABinaryTreeII {

	private boolean qFound = false;
	private boolean pFound = false;

	// time O(n), space O(n)
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode LCA = dfs(root, p, q);
		return qFound && pFound ? LCA : null;
	}

	private TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return root;
		}
		TreeNode left = dfs(root.left, p, q);
		TreeNode right = dfs(root.right, p, q);
		if (root == p) {
			pFound = true;
			return root;
		}
		if (root == q) {
			qFound = true;
			return root;
		}
		if (left == null) {
			return right;
		} else if (right == null) {
			return left;
		}
		return root;
	}
}
