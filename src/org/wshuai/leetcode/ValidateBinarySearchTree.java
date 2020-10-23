package org.wshuai.leetcode;

/**
 * Created by Wei on 01/13/2020.
 * #0098 https://leetcode.com/problems/validate-binary-search-tree/
 */
public class ValidateBinarySearchTree {

	// time O(n)
	public boolean isValidBST(TreeNode root) {
		return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public boolean dfs(TreeNode root, long minVal, long maxVal) {
		if (root == null){
			return true;
		}
		if (root.val >= maxVal || root.val <= minVal){
			return false;
		}
		return dfs(root.left, minVal, root.val)
				&& dfs(root.right, root.val, maxVal);
	}
}
