package org.wshuai.leetcode;

/**
 * Created by Wei on 09/11/2019.
 * #0979 https://leetcode.com/problems/distribute-coins-in-binary-tree/
 */
public class DistributeCoinsInBinaryTree {
	private static final int MASK = (1 << 10) - 1;
	private int res = 0;

	// time O(n), space O(n)
	public int distributeCoins(TreeNode root) {
		res = 0;
		dfs(root);
		return res;
	}

	private int dfs(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = dfs(root.left), right = dfs(root.right);
		int leftCount = left & MASK, leftCoins = left >> 10;
		int rightCount = right & MASK, rightCoins = right >> 10;
		res += Math.abs(leftCoins - leftCount) + Math.abs(rightCoins - rightCount);
		return ((leftCoins + rightCoins + root.val) << 10) + leftCount + rightCount + 1;
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
