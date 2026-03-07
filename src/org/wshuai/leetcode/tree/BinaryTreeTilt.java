package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 07/24/2017.
 * #0563 https://leetcode.com/problems/binary-tree-tilt/
 */
public class BinaryTreeTilt {
    private int res;

    // time O(n), space O(h)
    public int findTilt(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left), right = dfs(root.right);
        res += Math.abs(left - right);
        return root.val + left + right;
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
