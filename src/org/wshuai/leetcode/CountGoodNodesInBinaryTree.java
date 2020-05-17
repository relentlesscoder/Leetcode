package org.wshuai.leetcode;

/**
 * Created by Wei on 05/17/2020.
 * #1448 https://leetcode.com/problems/count-good-nodes-in-binary-tree/
 */
public class CountGoodNodesInBinaryTree {

	// time O(n)
	public int goodNodes(TreeNode root) {
		int[] arr = new int[]{0};
		dfs(root, arr, Integer.MIN_VALUE);
		return arr[0];
	}

	private void dfs(TreeNode root, int[] arr, int max){
		if(root == null){
			return;
		}
		arr[0] += root.val >= max ? 1 : 0;
		max = Math.max(max, root.val);
		dfs(root.left, arr, max);
		dfs(root.right, arr, max);
	}
}
