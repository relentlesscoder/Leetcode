package org.wshuai.leetcode;

/**
 * Created by Wei on 11/06/2019.
 * #0663 https://leetcode.com/problems/equal-tree-partition/
 */
public class EqualTreePartition {
	private boolean res = false;
	private int total = -1;

	// time O(n), space O(h)
	public boolean checkEqualTree(TreeNode root) {
		res = false;
		total = dfs(root, root);
		dfs(root, root);
		return res;
	}

	private int dfs(TreeNode node, TreeNode root) {
		if (node == null || res) { // 优化 - 退出递归如果 res 已经为真
			return 0;
		}
		int left = dfs(node.left, root), // 左子树节点值之和
				right = dfs(node.right, root), // 右子树节点值之和
				sum = left + right + node.val; // 当前子树值之和
		// 如果 total 已经算出来了，且 total 两倍于 sum， 且当前节点不是树
		// 的根结点则可以均匀划分。
		if (total != -1 && total - sum == sum && node != root) {
			res = true;
		}
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
