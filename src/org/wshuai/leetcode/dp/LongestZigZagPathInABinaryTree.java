package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 03/08/2020.
 * #1372 https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
 */
public class LongestZigZagPathInABinaryTree {
    private int res = 0;

    // time O(n), space O(h)
    public int longestZigZag(TreeNode root) {
        dfs(root, 0, 0);
        return res;
    }

    private void dfs(TreeNode root, int left, int right) {
        // left 表示从左边到达当前节点 (即当前节点为其父节点的左子节点) 的交错路径
        // 上节点的个数
        // right 表示从右边到达当前节点 (即当前节点为其父节点的右子节点) 的交错路径
        // 上节点的个数
        res = Math.max(res, Math.max(left, right));
        if (root.left != null) { // 如果当前节点存在左子节点
            // 则到达左子节点的交错路径是 right + 1 - 延续从右边到达当前节点的交错
            // 路径的长度。而左子节点的右侧交错路径只能从 0 开始。
            dfs(root.left, right + 1, 0);
        }
        if (root.right != null) { // 如果当前节点存在右子节点
            // 则到达右子节点的交错路径是 left + 1 - 延续从左边到达当前节点的交错
            // 路径的长度。而右子节点的左侧交错路径只能从 0 开始。
            dfs(root.right, 0, left + 1);
        }
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
