package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 08/24/2019.
 * #1038 https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 */
public class BinarySearchTreeToGreaterSumTree {

    // time O(n), space O(h)
    public TreeNode bstToGst(TreeNode root) {
        // 同 #0538
        dfs(root, 0);
        return root;
    }

    private int dfs(TreeNode root, int add) {
        if (root == null) {
            return 0;
        }
        int right = dfs(root.right, add);
        int left = dfs(root.left, add + right + root.val);
        int val = root.val;
        root.val += right + add;
        return right + val + left;
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
