package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 08/20/2019.
 * #0993 https://leetcode.com/problems/cousins-in-binary-tree/
 */
public class CousinsInBinaryTree {

    // time O(n), space O(n)
    private int xDepth = -1;
    private int yDepth = -1;
    private boolean sameParent = false;

    public boolean isCousins(TreeNode root, int x, int y) {
        xDepth = -1;
        yDepth = -1;
        sameParent = false;
        dfs(root, x, y, 0);
        return xDepth == yDepth && !sameParent;
    }

    private void dfs(TreeNode root, int x, int y, int depth) {
        if (root == null) {
            return;
        }
        if (root.val == x) {
            xDepth = depth;
        } else if (root.val == y) {
            yDepth = depth;
        }
        if (root.left != null && root.right != null
                && ((root.left.val == x && root.right.val == y)
                || (root.right.val == x && root.left.val == y))) {
            sameParent = true;
        }
        dfs(root.left, x, y, depth + 1);
        dfs(root.right, x, y, depth + 1);
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
