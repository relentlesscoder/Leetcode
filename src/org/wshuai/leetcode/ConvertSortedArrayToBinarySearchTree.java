package org.wshuai.leetcode;

/**
 * Created by Wei on 01/31/2016.
 * #0108 https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class ConvertSortedArrayToBinarySearchTree {

	// time O(n), space O(log(n))
	public TreeNode sortedArrayToBST(int[] nums) {
		int n = nums.length;
		return dfs(nums, 0, n - 1);
	}

	private TreeNode dfs(int[] nums, int start, int end) {
		// 每次将原问题划分为两个子问题，每次将一个元素转换成节点
		if (start > end) {
			return null;
		}
		if (start == end) {
			return new TreeNode(nums[start]);
		}
		// 将中间的元素作为当前子树的根结点
		int mid = start + (end - start) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		// 递归左子问题 [start, mid - 1]
		root.left = dfs(nums, start, mid - 1);
		// 递归右子问题 [mid - 1, end]
		root.right = dfs(nums, mid + 1, end);
		return root;
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
