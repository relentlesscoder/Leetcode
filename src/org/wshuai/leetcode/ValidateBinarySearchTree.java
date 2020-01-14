package org.wshuai.leetcode;

/**
 * Created by Wei on 01/13/2020.
 * #0098 https://leetcode.com/problems/validate-binary-search-tree/
 */
public class ValidateBinarySearchTree {
	// time O(n)
	public boolean isValidBST(TreeNode root) {
		long[] res = dfs(root);
		return res[0] == 1L;
	}

	private long[] dfs(TreeNode node){
		if(node == null){
			return new long[]{1L, Long.MIN_VALUE, Long.MAX_VALUE};
		}
		if(node.left == null && node.right == null){
			return new long[]{1L, node.val, node.val};
		}
		long[] left = dfs(node.left);
		long[] right = dfs(node.right);
		long valid = node.val > left[1] && node.val < right[2] && left[0] == 1L && right[0] == 1L ? 1L : 0L;
		return new long[]{valid, Math.max(node.val, right[1]), Math.min(node.val, left[2])};
	}
}
