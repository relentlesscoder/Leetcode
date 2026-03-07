package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 08/08/2019.
 * #0700 https://leetcode.com/problems/search-in-a-binary-search-tree/
 */
public class SearchInABinarySearchTree {

    // time O(n), space O(1)
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (root.val < val) {
                root = root.right;
            } else if (root.val > val) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
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
