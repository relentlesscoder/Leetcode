package org.wshuai.leetcode;

/**
 * Created by Wei on 08/09/2019.
 * #0965 https://leetcode.com/problems/univalued-binary-tree/
 */
public class UnivaluedBinaryTree {

    // time O(n), space O(h)
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = root.left == null ? root.val : root.left.val;
        int right = root.right == null ? root.val : root.right.val;
        return root.val == left
                && root.val == right
                && isUnivalTree(root.left)
                && isUnivalTree(root.right);
    }

    // time O(n), space O(h)
    public boolean isUnivalTreeTopDown(TreeNode root) {
        return dfs(root, root.val);
    }

    private boolean dfs(TreeNode root, int val) {
        if (root == null) {
            return true;
        }
        if (root.val != val) {
            return false;
        }
        return dfs(root.left, val) && dfs(root.right, val);
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
