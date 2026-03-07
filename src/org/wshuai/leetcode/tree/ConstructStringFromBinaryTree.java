package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 07/20/2017.
 * #0606 https://leetcode.com/problems/construct-string-from-binary-tree/
 */
public class ConstructStringFromBinaryTree {

    private StringBuilder res = new StringBuilder();

    // time O(n), space O(n)
    public String tree2str(TreeNode root) {
        res = new StringBuilder();
        dfs(root);
        return res.toString();
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        res.append(root.val);
        // 左节点不为空，递归左节点并用括号围起来
        if (root.left != null) {
            res.append("(");
            dfs(root.left);
            res.append(")");
        } else if (root.right != null) {
            // 左节点为空而右节点不为空，则写个空括号
            res.append("()");
        }
        // 右节点不为空，递归右节点并用括号围起来
        if (root.right != null) {
            res.append("(");
            dfs(root.right);
            res.append(")");
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
