package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 08/09/2019.
 * #0669 https://leetcode.com/problems/trim-a-binary-search-tree/
 */
public class TrimABinarySearchTree {

    // time O(n), space O(n)
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        // 递归左子树
        TreeNode left = trimBST(root.left, low, high);
        // 递归右子树
        TreeNode right = trimBST(root.right, low, high);
        // 如果当前节点小于 low ，则返回递归右子树结果因为左子树一定都小于 low
        if (root.val < low) {
            return right;
        }
        // 如果当前节点大于 high ，则返回递归左子树结果因为右子树一定都大于 high
        if (root.val > high) {
            return left;
        }
        // 如果当前节点在范围内则将左右子树分别设为递归的结果
        root.left = left;
        root.right = right;
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
