package org.wshuai.leetcode;

/**
 * Created by Wei on 10/28/2016.
 * #0298 https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
 */
public class BinaryTreeLongestConsecutiveSequence {

    private int res = 0;

    // time O(n), space O(h)
    public int longestConsecutive(TreeNode root) {
        res = 0;
        dfs(root, Integer.MIN_VALUE, 0);
        return res;
    }

    private void dfs(TreeNode root, int parent, int len) {
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
        dfs(root.left, root.val, len);
		// 递归右子树
        dfs(root.right, root.val, len);
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
