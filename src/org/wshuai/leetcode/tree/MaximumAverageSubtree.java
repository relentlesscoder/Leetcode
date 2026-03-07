package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 09/06/2019.
 * #1120 https://leetcode.com/problems/maximum-average-subtree/
 */
public class MaximumAverageSubtree {

    private static final long MASK = (1L << 15) - 1;
    private double res = 0.0;

    // time O(n), space O(h)
    public double maximumAverageSubtree(TreeNode root) {
        res = 0.0;
        dfs(root);
        return res;
    }

    private long dfs(TreeNode root) {
        // 状态压缩，用长整型的低 15 比特位存以当前节点为根结点的子树的节点数而用高位存子树的节点和。
        if (root == null) {
            return 0L;
        }
        // 递归左右子树
        long left = dfs(root.left), right = dfs(root.right);
        // 提取子树和以及子树节点数
        long leftSum = left >> 15, leftCount = left & MASK;
        long rightSum = right >> 15, rightCount = right & MASK;
        long sum = leftSum + rightSum + root.val, count = leftCount + rightCount + 1;
        // 计算平均值
        res = Math.max(res, 1.0 * sum / count);
        // 压缩状态并返回
        return (sum << 15) + count;
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
