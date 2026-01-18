package org.wshuai.leetcode;

/**
 * Created by Wei on 01/23/2016.
 * #0110 https://leetcode.com/problems/balanced-binary-tree/
 */
public class BalancedBinaryTree {

    private boolean balanced;

    // time O(n), space O(h)
    public boolean isBalanced(TreeNode root) {
        balanced = true;
        dfs(root);
        return balanced;
    }

    private int dfs(TreeNode root) {
		// 对每个节点判断左右子树高度差是否小于等于 1 。
        if (root == null || !balanced) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (Math.abs(left - right) > 1) {
            balanced = false;
        }
        return Math.max(left, right) + 1;
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
