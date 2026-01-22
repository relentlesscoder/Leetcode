package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2016.
 * #0270 https://leetcode.com/problems/closest-binary-search-tree-value/
 */
public class ClosestBinarySearchTreeValue {

    // time O(n), space O(n)
    public int closestValue(TreeNode root, double target) {
        int res = -1;
        double diff = Double.MAX_VALUE;
        while (root != null) {
            if (root.val > target) {
                double d = root.val - target;
                if (d < diff || (d == diff && root.val < res)) {
                    diff = d;
                    res = root.val;
                }
                root = root.left;
            } else {
                double d = target - root.val;
                if (d < diff || (d == diff && root.val < res)) {
                    diff = d;
                    res = root.val;
                }
                root = root.right;
            }
        }
        return res;
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
