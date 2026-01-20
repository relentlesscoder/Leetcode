package org.wshuai.leetcode;

/**
 * Created by Wei on 04/03/2017.
 * #0543 https://leetcode.com/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTree {

    private int res = 0;

    // time O(n), space O(h)
    public int diameterOfBinaryTree(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        // dfs 计算以当前节点为根结点的最长链 - 根结点到叶节点的最长距离
        if (root == null) {
            return -1;
        }
        // 递归计算左右子树的最长链
        int left = dfs(root.left), right = dfs(root.right);
        // 经过当前节点的最长路径为左右子树的最长链 + 当前节点到左右节点的两条边
        res = Math.max(res, left + right + 2);
        // 最长链只能取左右子树较长的那条 + 当前节点到此节点的那条边
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
