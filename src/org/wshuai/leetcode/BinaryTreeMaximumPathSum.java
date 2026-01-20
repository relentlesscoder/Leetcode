package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0124 https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class BinaryTreeMaximumPathSum {

	private int res = Integer.MIN_VALUE;

	// time O(n), space O(h)
	public int maxPathSum(TreeNode root) {
		// 同 #0543 思路
		res = Integer.MIN_VALUE;
		dfs(root);
		return res;
	}

	private int dfs(TreeNode root) {
		if (root == null) {
			return 0;
		}
		// 递归计算左右子树的最大路径和
		int left = dfs(root.left), right = dfs(root.right);
		// 更新最大值
		res = Math.max(res, root.val + left + right);
		// 注意如果路径和为负则丢弃不用 - 返回 0
		return Math.max(Math.max(left, right) + root.val, 0);
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
