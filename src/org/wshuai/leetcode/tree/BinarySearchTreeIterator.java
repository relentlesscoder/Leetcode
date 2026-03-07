package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 01/26/2016.
 * #0173 https://leetcode.com/problems/binary-search-tree-iterator/
 */
public class BinarySearchTreeIterator {

	// time O(n), space O(1)
    private static class BSTIterator {

        private TreeNode curr;

        public BSTIterator(TreeNode root) {
            curr = root;
        }

        public int next() {
            while (curr != null) {
                if (curr.left == null) {
                    int res = curr.val;
                    curr = curr.right;
                    return res;
                } else {
                    TreeNode pre = curr.left;
                    while (pre.right != null && pre.right != curr) {
                        pre = pre.right;
                    }
                    if (pre.right == null) {
                        pre.right = curr;
                        curr = curr.left;
                    } else {
                        int res = curr.val;
                        pre.right = null;
                        curr = curr.right;
                        return res;
                    }
                }
            }
            return -1;
        }

        public boolean hasNext() {
            return curr != null;
        }
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

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
