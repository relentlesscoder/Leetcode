package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 02/20/2017.
 * #0333 https://leetcode.com/problems/largest-bst-subtree/
 */
public class LargestBSTSubtree {

	private static final long MASK = (1L << 15) - 1;
	private static final long MAX = 20_000;
	private int res = 0;

	// time O(n), space O(h)
	public int largestBSTSubtree(TreeNode root) {
		res = 0;
		dfs(root);
		return res;
	}

	private long dfs(TreeNode root) {
		// 状态压缩，用一个长整型的从低到高的比特位存以当前节点为根结点的子树的以下状态:
		// 比特位 15  |  15 | 15   | 1
		// 状态   max | min | size | bst
		// 注意需要把包含负数的值域转化为全正数 [-10^4, 10^4] -> [0, 2 * 10^4]
		if (root == null) {
			// 默认状态: 0 | 2 * 10^4 | 0 | 1
			return (MAX << 16) + 1;
		}
		// 递归左右子树
		long left = dfs(root.left), right = dfs(root.right);
		// 提取状态值
		long leftMax = left >> 31, leftMin = (left >> 16) & MASK, leftCount = (left >> 1) & MASK, leftTree = left & 1;
		long rightMax = right >> 31, rightMin = (right >> 16) & MASK, rightCount = (right >> 1) & MASK, rightTree = right & 1;
		int size = (int) (leftCount + rightCount + 1), // 当前子树的大小
				bst = 0, // 变量 bst 的值 1 代表当前子树是为 BST，0 表示不是
				val = root.val + 10_000; // 映射当前节点值
		// 判断当前子树是否为 BST
		if (leftTree == 1 && rightTree == 1 && leftMax < val && rightMin > val) {
			res = Math.max(res, size);
			bst = 1;
		}
		// 更新最大最小值
		long max = Math.max(val, Math.max(leftMax, rightMax));
		long min = Math.min(val, Math.min(leftMin, rightMin));
		// 状态   max | min | size | bst
		return (max << 31) + (min << 16) + (size << 1) + bst;
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
