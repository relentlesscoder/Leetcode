package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 02/03/2020.
 * #1339 https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
 */
public class MaximumProductOfSplittedBinaryTree {

	private static final int MOD = (int) 1e9 + 7;
	private long res = 1L;

	// time O(n), space O(h)
	public int maxProduct(TreeNode root) {
		res = 1L;
		// 计算所有节点之和 total
		int total = calcTotal(root);
		// 对每个节点，计算以它为根结点的子树的和 sum 与树中其他节点和 total - sum
		// 的乘积, 即以当前节点与其父节点之间的边分裂二叉树形成的两个部分和的乘积。
		calcProd(root, total);
		return (int) (res % MOD);
	}

	private int calcTotal(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return root.val + calcTotal(root.left) + calcTotal(root.right);
	}

	private int calcProd(TreeNode root, int total) {
		if (root == null) {
			return 0;
		}
		int left = calcProd(root.left, total),
				right = calcProd(root.right, total);
		int sum = root.val + left + right;
		res = Math.max(res, (long) sum * (total - sum));
		return sum;
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
