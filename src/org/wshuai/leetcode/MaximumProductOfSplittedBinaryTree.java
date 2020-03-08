package org.wshuai.leetcode;

/**
 * Created by Wei on 02/03/2020.
 * #1343 https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
 */
public class MaximumProductOfSplittedBinaryTree {
	private long max = Long.MIN_VALUE, sum = 0;

	// time O(n)
	public int maxProduct(TreeNode root) {
		if(root == null){
			return 0;
		}
		sum = dfs(root);
		max = Long.MIN_VALUE;
		dfs(root);
		return (int)(max % 1_000_000_007);
	}

	private long dfs(TreeNode root){
		if(root == null){
			return 0;
		}
		long nodeSum = dfs(root.left) + dfs(root.right) + root.val;
		max = Math.max(max, nodeSum * (sum - nodeSum));
		return nodeSum;
	}
}
