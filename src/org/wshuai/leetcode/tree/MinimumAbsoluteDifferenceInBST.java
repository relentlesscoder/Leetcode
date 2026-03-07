package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 03/07/2017.
 * #0530 https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 */
public class MinimumAbsoluteDifferenceInBST {

    private int res = Integer.MAX_VALUE;
    private int last = -1;

    public int getMinimumDifference(TreeNode root) {
		// 二叉搜索树的中序遍历就是排序后的顺序，所以每个值与前一个值的差就是最小的
        res = Integer.MAX_VALUE;
        last = -1;
        inorder(root);
        return res;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (last != -1) {
            res = Math.min(res, root.val - last);
        }
        last = root.val;
        inorder(root.right);
    }

    // time O(n), space O(1)
    public int getMinimumDifferenceMorris(TreeNode root) {
        int res = Integer.MAX_VALUE, last = -1;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                if (last != -1) {
                    res = Math.min(res, curr.val - last);
                }
                last = curr.val;
                curr = curr.right;
            } else {
                TreeNode node = curr.left;
                while (node.right != null && node.right != curr) {
                    node = node.right;
                }
                if (node.right == null) {
                    node.right = curr;
                    curr = curr.left;
                } else {
                    node.right = null;
                    if (last != -1) {
                        res = Math.min(res, curr.val - last);
                    }
                    last = curr.val;
                    curr = curr.right;
                }
            }
        }
        return res;
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
