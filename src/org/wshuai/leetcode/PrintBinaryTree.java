package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/27/2019.
 * #0655 https://leetcode.com/problems/print-binary-tree/
 */
public class PrintBinaryTree {

	// time O(n), space O(n)
	public List<List<String>> printTree(TreeNode root) {
		// 毫无技巧按照题目要求干就完事了
		// DFS 计算树的高度以确定 m 和 n
		int m = getHeight(root), n = (1 << m) - 1;
		// 构造 m x n 矩阵
		List<List<String>> res = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			List<String> row = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				row.add("");
			}
			res.add(row);
		}
		// DFS 填充矩阵
		fill(root, 0, (n - 1) / 2, m - 1, res);
		return res;
	}

	private void fill(TreeNode root, int r, int c, int h, List<List<String>> matrix) {
		if (root == null) {
			return;
		}
		matrix.get(r).set(c, Integer.toString(root.val));
		int d = 1 << (h - r - 1);
		fill(root.left, r + 1, c - d, h, matrix);
		fill(root.right, r + 1, c + d, h, matrix);
	}

	private int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(getHeight(root.left), getHeight(root.right));
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
