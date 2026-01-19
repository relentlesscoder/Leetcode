package org.wshuai.leetcode;

/**
 * Created by Wei on 10/28/2016.
 * #0298 https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
 */
public class BinaryTreeLongestConsecutiveSequence {

    private int res = 0;

    // time O(n), space O(h)
    public int longestConsecutiveTopDown(TreeNode root) {
        res = 0;
        dfsTopDown(root, Integer.MIN_VALUE, 0);
        return res;
    }

    private void dfsTopDown(TreeNode root, int parent, int len) {
        // parent 表示父节点的值而 len 表示到达当前节点前最长序列的长度。递归二叉树，
        // 对每个节点统计以该节点结尾的最长序列的长度。
        if (root == null) {
            return;
        }
        // 如果当前节点的值等于 parent + 1，则延续最长序列的值加一。否则
        // 重置最长序列的值为 1 。
        len = root.val == parent + 1 ? len + 1 : 1;
        // 更新答案
        res = Math.max(res, len);
        // 递归左子树
        dfsTopDown(root.left, root.val, len);
        // 递归右子树
        dfsTopDown(root.right, root.val, len);
    }

    // time O(n), space O(h)
    public int longestConsecutiveBottomUp(TreeNode root) {
        res = 0;
        dfsBottomUp(root);
        return res;
    }

    private int dfsBottomUp(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfsBottomUp(root.left), right = dfsBottomUp(root.right);
        int len1 = root.left == null || root.val != root.left.val - 1 ? 1 : left + 1;
        int len2 = root.right == null || root.val != root.right.val - 1 ? 1 : right + 1;
        int len = Math.max(len1, len2);
        res = Math.max(res, len);
        return len;
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
