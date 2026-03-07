package org.wshuai.leetcode.other;

/**
 * Created by Wei on 01/18/2026.
 * #LCP67 https://leetcode.cn/problems/KnLfVT/
 */
public class LCP67 {

    // time O(n), space O(h)
    public TreeNode expandBinaryTree(TreeNode root) {
        // 递归二叉树
        if (root == null) {
            return null;
        }
        TreeNode left = expandBinaryTree(root.left),
                right = expandBinaryTree(root.right);
        // 装饰
        if (left != null) {
            root.left = new TreeNode(-1, left, null);
        }
        if (right != null) {
            root.right = new TreeNode(-1, null, right);
        }
        return root;
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
