package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 10/28/2019.
 * #1080 https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths/
 */
public class InsufficientNodesInRootToLeafPaths {

    // time O(n), space O(h)
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        limit -= root.val;
        // 从根结点到叶子节点的路径只有一条
        if (root.left == null && root.right == null) {
            return limit > 0 ? null : root;
        }
        // 递归左子树
        if (root.left != null) {
            root.left = sufficientSubset(root.left, limit);
        }
        // 递归右子树
        if (root.right != null) {
            root.right = sufficientSubset(root.right, limit);
        }
        // 节点能被删除的条件是左右子节点都可以被删除，因为如果节点能被删除经过该节点到所有叶子
        // 节点的路径的和都小于 limit 所有路径都必须经过它的两个子节点。
        return root.left == null && root.right == null ? null : root;
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
