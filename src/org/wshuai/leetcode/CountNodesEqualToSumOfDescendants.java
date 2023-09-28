package org.wshuai.leetcode;

/**
 * Created by Wei on 09/28/2023.
 * #1973 https://leetcode.com/problems/count-nodes-equal-to-sum-of-descendants/
 */
public class CountNodesEqualToSumOfDescendants {

	// time O(n), space O(n)
	public int equalToDescendants(TreeNode root) {
		return (int) dfs(root)[0];
	}

	private long[] dfs(TreeNode node) {
		if (node == null) {
			return new long[] {0, 0};
		}
		long[] left = dfs(node.left), right = dfs(node.right);
		long sum = left[1] + right[1], count = left[0] + right[0];
		return new long[] {count + (sum == node.val ? 1 : 0), sum + node.val};
	}

	/**
	 * Definition for a binary tree node.
	 */
	  private class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode() {}
	      TreeNode(int val) { this.val = val; }
	      TreeNode(int val, TreeNode left, TreeNode right) {
	          this.val = val;
	          this.left = left;
	          this.right = right;
	      }
	  }
}
