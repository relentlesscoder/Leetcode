package org.wshuai.leetcode;

/**
 * Created by Wei on 09/28/2019.
 * #0951 https://leetcode.com/problems/flip-equivalent-binary-trees/
 */
public class FlipEquivalentBinaryTrees {

    // time O(n), space O(h)
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // 同时递归两个二叉树对应的节点，如果节点不一样则需要翻转。如果所有节点能两两相等
        // 则两二叉树是等价的。

        // 两个节点同时为空
        if (root1 == null) {
            return root2 == null;
        }
        if (root2 == null) {
            return root1 == null;
        }
        // 两个节点必须等值
        if (root1.val != root2.val) {
            return false;
        }
        // 如果两个节点的左子节点值不同则翻转 root2
        if ((root1.left == null && root2.left == null)
                || (root1.left != null && root2.left != null && root1.left.val == root2.left.val)) {
            // 不用真的翻转而是改变遍历顺序
            return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
        } else {
            // 如果两个节点的左子节点值相同则无需翻转
            return flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
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
}
