package org.wshuai.leetcode;

/**
 * Created by Wei on 03/09/2020.
 * #1373 https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
 */
public class MaximumSumBSTInBinaryTree {

	private int res = 0;

	// time O(n), space O(n)
	public int maxSumBST(TreeNode root) {
		res = 0; // 当二叉树为空最小和为 0
		dfs(root);
		return res;
	}

	private int[] dfs(TreeNode root) {
		if (root == null) {
			// 返回的数组的值分别代表以当前节点为根结点的子树的最大值, 最小值, 节点和,
			// 以及是否是二叉搜索树
			return new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1};
		}
		// 递归左右子树
		int[] left = dfs(root.left), right = dfs(root.right);
		// 更新最大值
		int max = Math.max(root.val, Math.max(left[0], right[0]));
		// 更新最小值
		int min = Math.min(root.val, Math.min(left[1], right[1]));
		// 计算节点和
		int sum = root.val + left[2] + right[2];
		int bst = 0;
		// 子树为 BST 的条件:
		//   1. 左子树是 BST
		//   2. 右子树是 BST
		//   3. 当前节点大于左子树的最大值
		//   4. 当前节点小于右子树的最小值
		if (left[3] == 1 && right[3] == 1 && root.val > left[0] && root.val < right[1]) {
			res = Math.max(res, sum); // 更新答案
			bst = 1;
		}
		return new int[] {max, min, sum, bst};
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
