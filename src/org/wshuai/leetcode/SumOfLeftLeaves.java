package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2016.
 * #0404 https://leetcode.com/problems/sum-of-left-leaves/
 */
public class SumOfLeftLeaves {

    // time O(n), space O(h)
    public int sumOfLeftLeaves(TreeNode root) {
        return calc(root, false);
    }

    private int calc(TreeNode node, boolean left) {
        if (left && node.left == null && node.right == null) {
            return node.val;
        }
        int sum = 0;
        if (node.left != null) {
            sum += calc(node.left, true);
        }
        if (node.right != null) {
            sum += calc(node.right, false);
        }
        return sum;
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
