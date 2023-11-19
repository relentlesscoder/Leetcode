package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2023.
 * #2689 https://leetcode.com/problems/extract-kth-character-from-the-rope-tree/
 */
public class ExtractKthCharacterFromTheRopeTree {

	// time O(n), space O(n)
	public char getKthCharacter(RopeTreeNode root, int k) {
		String str = dfs(root);
		return str.charAt(k - 1);
	}

	private String dfs(RopeTreeNode node) {
		if (node.left == null && node.right == null) {
			return node.val;
		}
		String left = node.left != null ? dfs(node.left) : "",
				right = node.right != null ? dfs(node.right) : "";
		return left + right;
	}

	/**
	 * Definition for a rope tree node.
	 */
	private class RopeTreeNode {
		int len;
		String val;
		RopeTreeNode left;
		RopeTreeNode right;

		RopeTreeNode() {
		}

		RopeTreeNode(String val) {
			this.len = 0;
			this.val = val;
		}

		RopeTreeNode(int len) {
			this.len = len;
			this.val = "";
		}

		RopeTreeNode(int len, RopeTreeNode left, RopeTreeNode right) {
			this.len = len;
			this.val = "";
			this.left = left;
			this.right = right;
		}
	}
}
