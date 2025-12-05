package org.wshuai.leetcode;

/**
 * Created by Wei on 08/20/2019.
 * #0993 https://leetcode.com/problems/cousins-in-binary-tree/
 */
public class CousinsInBinaryTree {

	// time O(n), space O(n)
	public boolean isCousins(TreeNode root, int x, int y) {
		int[] info = new int[4];
		dfs(root, x, y, -1, 0, info);
		return info[0] != info[2] && info[1] == info[3];
	}

	private void dfs(TreeNode node, int x, int y, int parent, int level, int[] info) {
		if (node == null) {
			return;
		}
		if (node.val == x) {
			info[0] = parent;
			info[1] = level;
		}
		if (node.val == y) {
			info[2] = parent;
			info[3] = level;
		}
		dfs(node.left, x, y, node.val, level + 1, info);
		dfs(node.right, x, y, node.val, level + 1, info);
	}

	/**
	 * Definition for a binary tree node.
	 */
	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
