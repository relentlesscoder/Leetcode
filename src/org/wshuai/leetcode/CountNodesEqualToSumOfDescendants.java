package org.wshuai.leetcode;

/**
 * Created by Wei on 09/28/2023.
 * #1973 https://leetcode.com/problems/count-nodes-equal-to-sum-of-descendants/
 */
public class CountNodesEqualToSumOfDescendants {

	private int res = 0;

	// time O(n), space O(h)
	public int equalToDescendants(TreeNode root) {
		res = 0;
		dfs(root);
		return res;
	}

	private int dfs(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = dfs(root.left), right = dfs(root.right);
		if (left + right == root.val) {
			res++;
		}
		return root.val + left + right;
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
