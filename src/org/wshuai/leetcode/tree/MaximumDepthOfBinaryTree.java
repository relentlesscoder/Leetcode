package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 01/19/2016.
 * #0104 https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class MaximumDepthOfBinaryTree {

    // time O(n), space O(h)
    public int maxDepthBottomUp(TreeNode root) {
        // 自底向上
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepthBottomUp(root.left),
                maxDepthBottomUp(root.right));
    }

    private int res;

    // time O(n), space O(h)
    public int maxDepthTopDown(TreeNode root) {
        // 自顶向下
        res = 0;
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) {
            res = Math.max(res, depth);
            return;
        }
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
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
