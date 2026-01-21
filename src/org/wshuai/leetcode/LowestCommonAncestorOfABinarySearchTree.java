package org.wshuai.leetcode;

/**
 * Created by Wei on 01/23/2016.
 * #0235 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestorOfABinarySearchTree {

    // time O(n), space O(1)
    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
		// 对二叉搜索树的两个节点来说，最低公共祖先节点即它们所在子树的根结点。这个点
		// 需要满足条件:
		//   1. 大于等于两者的较小值
		//   2. 小于等于两者的较大值
		// 可以利用二叉树的性质进行搜素。
        int max = Math.max(p.val, q.val), min = Math.min(p.val, q.val);
        while (root != null && (root.val < min || root.val > max)) {
            if (root.val < min) {
                root = root.right;
            }
            if (root.val > max) {
                root = root.left;
            }
        }
        return root;
    }

    // time O(n), space O(n)
    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
		// 递归版本
        int max = Math.max(p.val, q.val), min = Math.min(p.val, q.val);
        return search(root, min, max);
    }

    private TreeNode search(TreeNode root, int min, int max) {
        if (root == null) {
            return null;
        }
        if (root.val < min) {
            return search(root.right, min, max);
        }
        if (root.val > max) {
            return search(root.left, min, max);
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
