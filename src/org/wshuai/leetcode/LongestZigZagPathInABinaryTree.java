package org.wshuai.leetcode;

/**
 * Created by Wei on 03/08/2020.
 * #1372 https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
 */
public class LongestZigZagPathInABinaryTree {
	private int max;

	// time O(n)
	public int longestZigZag(TreeNode root) {
		max = 0;
		if(root == null){
			return max;
		}
		dfs(root);
		return max - 1;
	}

	private int[] dfs(TreeNode root){
		if(root == null){
			return new int[]{0, 0};
		}
		int[] left = dfs(root.left);
		int[] right = dfs(root.right);
		int[] res = new int[]{1 + left[1], 1 + right[0]};
		max = Math.max(Math.max(res[0], res[1]), max);
		return res;
	}
}
