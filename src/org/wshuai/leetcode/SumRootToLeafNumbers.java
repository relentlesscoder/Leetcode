package org.wshuai.leetcode;

/**
 * Created by Wei on 10/29/2016.
 * #0129 https://leetcode.com/problems/sum-root-to-leaf-numbers/
 */
public class SumRootToLeafNumbers {

    private int res;

    // time O(n), space O(h)
    public int sumNumbers(TreeNode root) {
        res = 0;
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            res += sum;
            return;
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
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
