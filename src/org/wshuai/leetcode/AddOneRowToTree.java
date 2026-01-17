package org.wshuai.leetcode;

/**
 * Created by Wei on 10/30/2019.
 * #0623 https://leetcode.com/problems/add-one-row-to-tree/
 */
public class AddOneRowToTree {

    // time O(n), space O(h)
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        dfs(root, 1, val, depth);
        return root;
    }

    private void dfs(TreeNode root, int depth, int val, int target) {
        if (root == null) {
            return;
        }
        if (depth == target - 1) {
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
            return;
        }
        dfs(root.left, depth + 1, val, target);
        dfs(root.right, depth + 1, val, target);
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
