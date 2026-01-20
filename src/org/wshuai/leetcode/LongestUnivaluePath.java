package org.wshuai.leetcode;

/**
 * Created by Wei on 09/05/2019.
 * #0687 https://leetcode.com/problems/longest-univalue-path/
 */
public class LongestUnivaluePath {
    private int res = 0;

    // time O(n), space O(h)
    public int longestUnivaluePath(TreeNode root) {
		// 同 #0543 思路
        res = 0;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
		// dfs 求出以当前节点为根结点的最长同值路径
        if (root == null) {
            return 0;
        }
		// 递归求左右子节点的最长同值路径
        int leftMax = dfs(root.left), rightMax = dfs(root.right);
		// 如果当前节点值与左节点相同，则使用左节点的同值路径否则不能使用 left = 0 。
        int left = root.left == null || root.left.val != root.val ? 0 : leftMax;
		// 同样的如果当前节点值与右节点相同，则使用右节点的同值路径否则不能使用 right = 0 。
        int right = root.right == null || root.right.val != root.val ? 0 : rightMax;
		// 将左右路径拼在一起则形成一条新的路径，该路径即通过当前节点的最长同值路径。
        res = Math.max(res, left + right);
		// 以当前节点为根结点的最长同值路径即左右子节点同值路径更大的那一条加一。
        return Math.max(left, right) + 1;
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
