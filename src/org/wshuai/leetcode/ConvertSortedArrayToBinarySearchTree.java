package org.wshuai.leetcode;

/**
 * Created by Wei on 1/31/16.
 * #108 https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class ConvertSortedArrayToBinarySearchTree {
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		return GetRootNode(nums, 0, nums.length - 1);
	}

	private TreeNode GetRootNode(int[] input, int p, int q) {
		TreeNode root;
		if (p > q) {
			return null;
		}

		int idx = p + (q - p) / 2;
		int index = (p + q) % 2 == 0 ? idx : idx + 1;
		root = new TreeNode(input[index]);
		root.left = GetRootNode(input, p, index - 1);
		root.right = GetRootNode(input, index + 1, q);
		return root;
	}
}
