package org.wshuai.leetcode;

/**
 * Created by Wei on 01/15/2020.
 * #0111 https://leetcode.com/problems/minimum-depth-of-binary-tree/
 */
public class MinimumDepthOfBinaryTree {

    // time O(n), space O(h)
    public int minDepthBottomUp(TreeNode root) {
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
