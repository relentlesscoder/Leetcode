package org.wshuai.leetcode;

/**
 * Created by Wei on 01/31/2016.
 * #0108 https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class ConvertSortedArrayToBinarySearchTree {

	// time O(n)
	public TreeNode sortedArrayToBST(int[] nums) {
		if(nums == null || nums.length == 0){
			return null;
		}
		return merge(nums, 0, nums.length - 1);
	}

	private TreeNode merge(int[] nums, int i, int j){
		if(i > j){
			return null;
		}
		if(i == j){
			return new TreeNode(nums[i]);
		}
		int k = i + (j - i) / 2;
		TreeNode root = new TreeNode(nums[k]);
		root.left = merge(nums, i, k - 1);
		root.right = merge(nums, k + 1, j);
		return root;
	}
}
