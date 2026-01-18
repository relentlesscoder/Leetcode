package org.wshuai.leetcode;

/**
 * Created by Wei on 01/15/2020.
 * #0111 https://leetcode.com/problems/minimum-depth-of-binary-tree/
 */
public class MinimumDepthOfBinaryTree {

    // time O(n), space O(h)
    public int minDepthBottomUp(TreeNode root) {
        // 自底向上的简化版
        if (root == null) {
            return 0;
        }
        if (root.right == null) {
            return minDepthBottomUp(root.left) + 1;
        }
        if (root.left == null) {
            return minDepthBottomUp(root.right) + 1;
        }
        return Math.min(minDepthBottomUp(root.left),
                minDepthBottomUp(root.right)) + 1;
    }

    // time O(n), space O(h)
    public int minDepthBottomUpVerbose(TreeNode root) {
        // 自底向上
        if (root == null) {
            return 0;
        }
        // 叶子节点，左子节点和右子节点均为空
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 左不为空右为空
        if (root.left != null && root.right == null) {
            return minDepthBottomUpVerbose(root.left) + 1;
        }
        // 右不为空左为空
        if (root.right != null && root.left == null) {
            return minDepthBottomUpVerbose(root.right) + 1;
        }
        // 左右都不为空
        return Math.min(minDepthBottomUpVerbose(root.left),
                minDepthBottomUpVerbose(root.right)) + 1;
    }

    private int res;

    // time O(n), space O(h)
    public int minDepthTopDown(TreeNode root) {
        if (root == null) {
            return 0;
        }
        res = Integer.MAX_VALUE;
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null || ++depth >= res) {
            return;
        }
        if (node.left == null && node.right == null) {
            res = Math.min(res, depth);
            return;
        }
        dfs(node.left, depth);
        dfs(node.right, depth);
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
