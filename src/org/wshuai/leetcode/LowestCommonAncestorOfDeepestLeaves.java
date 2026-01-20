package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2019.
 * #1123 https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 */
public class LowestCommonAncestorOfDeepestLeaves {

    // time O(n), space O(h)
    public TreeNode lcaDeepestLeaves(TreeNode root) {
		// 同 #0865
        int depth = getDepth(root);
        return findCommonAncestor(root, depth, 0);
    }

    private TreeNode findCommonAncestor(TreeNode root, int depth, int curr) {
        if (root == null) {
            return null;
        }
        if (++curr == depth) {
            return root;
        }
        TreeNode left = findCommonAncestor(root.left, depth, curr);
        TreeNode right = findCommonAncestor(root.right, depth, curr);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
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
