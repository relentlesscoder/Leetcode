package org.wshuai.leetcode;

/**
 * Created by Wei on 11/13/2016.
 * #0250 https://leetcode.com/problems/count-univalue-subtrees/
 */
public class CountUnivalueSubtrees {
    private int res = 0;

    // time O(n), space O(h)
    public int countUnivalSubtrees(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    private boolean dfs(TreeNode root) {
		// 递归二叉树
        if (root == null) {
            return true;
        }
        boolean left = dfs(root.left), right = dfs(root.right);
		// 以当前节点为根结点的二叉树是同值二叉树的条件：
		//   1. 左右子树都是同值二叉树
		//   2. 左子节点为空或者与当前节点的值相同
		//   3. 右子节点为空或者与当前节点的值相同
        boolean valid = left && right
                && (root.left == null || root.left.val == root.val)
                && (root.right == null || root.right.val == root.val);
        if (valid) {
            res++;
        }
        return valid;
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
