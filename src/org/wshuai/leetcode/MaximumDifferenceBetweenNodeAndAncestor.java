package org.wshuai.leetcode;

/**
 * Created by Wei on 09/05/2019.
 * #1026 https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
 */
public class MaximumDifferenceBetweenNodeAndAncestor {

    private int res;

    // time O(n), space O(h)
    public int maxAncestorDiff(TreeNode root) {
        res = 0;
        // 递归二叉树，对每个节点传入从根结点到当前节点的值的最大值和最小值。
        // 答案即为所有最大值与最小值之差的最大值。
        dfs(root, -1, (int) 1e6);
        return res;
    }

    private void dfs(TreeNode root, int max, int min) {
        if (root == null) {
            return;
        }
		// 更新从根节点出发到当前节点 (包括) 的路径上所有节点的最大值
        max = Math.max(max, root.val);
		// 更新从根节点出发到当前节点 (包括) 的路径上所有节点的最小值
        min = Math.min(min, root.val);
        // 计算最大值与最小值之差并更新答案
        res = Math.max(res, max - min);
        dfs(root.left, max, min);
        dfs(root.right, max, min);
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
