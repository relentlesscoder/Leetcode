package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2017.
 * #0617 https://leetcode.com/problems/merge-two-binary-trees/
 */
public class MergeTwoBinaryTrees {

    //  time O(n), space O(h)
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
		// 不用另外新建一个二叉树，而是直接将 root2 合并到 root1 中。
		// 如果 root1 是空，则直接用对位的 root2 来替代。注意这里直接返回了，
		// 后续不会对 root2 继续遍历。
        if (root1 == null) {
            return root2;
        }
		// 如果 root2 是空，则直接返回 root1 。
        if (root2 == null) {
            return root1;
        }
		// 两个节点都不是空则合并它们的值。
        root1.val += root2.val;
		// 递归左子树
        root1.left = mergeTrees(root1.left, root2.left);
		// 递归右子树
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
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
