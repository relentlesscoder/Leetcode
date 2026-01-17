package org.wshuai.leetcode;

/**
 * Created by Wei on 08/10/2019.
 * #1022 https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/
 */
public class SumOfRootToLeafBinaryNumbers {

    // time O(n), space O(h)
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int num) {
        if (root == null) {
            return 0;
        }
        int v = (num << 1) + root.val;
        if (root.left == null && root.right == null) {
            return v;
        }
        return dfs(root.left, v) + dfs(root.right, v);
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
