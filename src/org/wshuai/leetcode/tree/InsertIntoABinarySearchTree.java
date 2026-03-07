package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 08/30/2019.
 * #0701 https://leetcode.com/problems/insert-into-a-binary-search-tree/
 */
public class InsertIntoABinarySearchTree {

    // time O(log(n)), space O(1)
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // CLRS, P294
        TreeNode curr = root, node = new TreeNode(val), parent = null;
        while (curr != null) {
            parent = curr;
            if (curr.val > val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        if (parent == null) {
            return node;
        } else if (parent.val > val) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        return root;
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
