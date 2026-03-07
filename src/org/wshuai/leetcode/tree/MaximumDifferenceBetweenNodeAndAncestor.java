package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 09/05/2019.
 * #1026 https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
 */
public class MaximumDifferenceBetweenNodeAndAncestor {

    private static final long MASK = (1L << 20) - 1;
    private static final long MAX = (long) 1e5 + 1;
    private int res = 0;

    // time O(n), space O(h)
    public int maxAncestorDiffTopDown(TreeNode root) {
        res = 0;
        // 递归二叉树，对每个节点传入从根结点到当前节点的值的最大值和最小值。
        // 答案即为所有最大值与最小值之差的最大值。
        dfsTopDown(root, -1, (int) 1e6);
        return res;
    }

    private void dfsTopDown(TreeNode root, int max, int min) {
        if (root == null) {
            return;
        }
        // 更新从根节点出发到当前节点 (包括) 的路径上所有节点的最大值
        max = Math.max(max, root.val);
        // 更新从根节点出发到当前节点 (包括) 的路径上所有节点的最小值
        min = Math.min(min, root.val);
        // 计算最大值与最小值之差并更新答案
        res = Math.max(res, max - min);
        dfsTopDown(root.left, max, min);
        dfsTopDown(root.right, max, min);
    }

    // time O(n), space O(h)
    public int maxAncestorDiffBottomUp(TreeNode root) {
        res = 0;
        dfsBottomUp(root);
        return res;
    }

    private long dfsBottomUp(TreeNode root) {
        // 状态压缩，用低 20 比特位存当前节点所有后辈节点的最小值而高位存其最大值。
        if (root == null) {
            return (-1L << 20) + MAX;
        }
        // 递归左右子树
        long left = dfsBottomUp(root.left), right = dfsBottomUp(root.right);
        // 从结果中分别抽出最大最小值
        int leftMax = (int) (left >> 20), leftMin = (int) (left & MASK);
        int rightMax = (int) (right >> 20), rightMin = (int) (right & MASK);
        // 更新当前节点为根结点子树的最大最小值
        int max = Math.max(root.val, Math.max(rightMax, leftMax));
        int min = Math.min(root.val, Math.min(rightMin, leftMin));
        // 注意这里必须用当前节点的值与最大最小值计算，如果直接用 max - min 则有可能两个
        // 值分别来之当前节点的左右子树则计算是错误的。
        res = Math.max(res, Math.max(root.val - min, max - root.val));
        // 压缩状态到长整型中并返回结果
        return ((long) max << 20) + min;
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
