package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 08/31/2019.
 * #0814 https://leetcode.com/problems/binary-tree-pruning/
 */
public class BinaryTreePruning {

    // time O(n), space O(h)
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
		// 递归左右子树
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
		// 子树能被删除的条件:
		//   1. 节点值为 0
		//   2. 左子树不包含 1 - 已被删除
		//   3. 右子树不包含 1 - 已被删除
        if (root.val == 0 && root.left == null && root.right == null) {
            return null; // 删除子树
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
