package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2023.
 * #2331 https://leetcode.com/problems/evaluate-boolean-binary-tree/
 */
public class EvaluateBooleanBinaryTree {

	// time O(n), space O(n)
	public boolean evaluateTree(TreeNode root) {
		if (root.left == null) {
			return root.val == 1;
		}
		boolean left = evaluateTree(root.left), right = evaluateTree(root.right);
		return (root.val == 2 ? left || right : left && right);
	}

	/**
	 * Definition for a binary tree node.
	 */
	public class TreeNode {
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
