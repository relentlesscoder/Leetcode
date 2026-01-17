package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0112 https://leetcode.com/problems/path-sum/
 */
public class PathSum {

    private boolean res;

    // time O(n), space O(h)
    public boolean hasPathSum(TreeNode root, int targetSum) {
        res = false;
        dfs(root, targetSum);
        return res;
    }

    private void dfs(TreeNode root, int target) {
        if (root == null || res) {
            return;
        }
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            res = true;
            return;
        }
        dfs(root.left, target);
        dfs(root.right, target);
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
