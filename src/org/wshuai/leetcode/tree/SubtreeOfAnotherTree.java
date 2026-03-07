package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 07/25/2017.
 * #0572 https://leetcode.com/problems/subtree-of-another-tree/
 */
public class SubtreeOfAnotherTree {

    private boolean res = false;

    // time O(n + m), space O(n + m)
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // 只判断 root 中那些与 subRoot 具有相同高度的节点
        res = false;
        // 计算 subRoot 的高度
        int h = getHeight(subRoot);
        // 递归 root
        dfs(root, subRoot, h);
        return res;
    }

    private int dfs(TreeNode root, TreeNode subRoot, int height) {
        if (root == null || res) {
            return 0;
        }
        int left = dfs(root.left, subRoot, height),
                right = dfs(root.right, subRoot, height);
        int h = Math.max(left, right) + 1;
        // 高度相同且树也相同
        if (h == height && isSame(root, subRoot)) {
            res = true;
        }
        return h;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left), right = getHeight(root.right);
        return Math.max(left, right) + 1;
    }

    // time O(m * n), space O(h)
    public boolean isSubtreeBF(TreeNode root, TreeNode subRoot) {
        // 暴力遍历 root 中的每个节点，判断它与 subRoot 是否一样。
        if (root == null) {
            return false;
        }
        return isSame(root, subRoot)
                || isSubtreeBF(root.left, subRoot)
                || isSubtreeBF(root.right, subRoot);
    }

    private boolean isSame(TreeNode node1, TreeNode node2) {
        if (node1 == null) {
            return node2 == null;
        }
        if (node2 == null) {
            return node1 == null;
        }
        if (node1.val != node2.val) {
            return false;
        }
        return isSame(node1.left, node2.left)
                && isSame(node1.right, node2.right);
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
