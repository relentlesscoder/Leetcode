package org.wshuai.leetcode;

/**
 * Created by Wei on 01/31/2016.
 * #0108 https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class ConvertSortedArrayToBinarySearchTree {
	// time O(n)
	public TreeNode sortedArrayToBST(int[] nums) {
		return dfs(nums, 0, nums.length - 1);
	}

	private TreeNode dfs(int[] nums, int i, int j){
		if(i > j){
			return null;
		}
		if(i == j){
			return new TreeNode(nums[i]);
		}
		int mid = i + (j - i) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = dfs(nums, i, mid - 1);
		root.right = dfs(nums, mid + 1, j);
		return root;
	}
}
