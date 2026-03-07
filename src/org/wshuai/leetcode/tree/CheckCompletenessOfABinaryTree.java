package org.wshuai.leetcode.tree;

import java.util.LinkedList;

/**
 * Created by Wei on 10/11/2019.
 * #0958 https://leetcode.com/problems/check-completeness-of-a-binary-tree/
 */
public class CheckCompletenessOfABinaryTree {

    // time O(n), space O(n)
    public boolean isCompleteTree(TreeNode root) {
        // 将二叉树的节点按层序遍历排序(包括空节点)，则非完全二叉树必含有空节点。
        TreeNode prev = root;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (prev == null && node != null) {
                return false;
            }
            if (node != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
            prev = node;
        }
        return true;
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
