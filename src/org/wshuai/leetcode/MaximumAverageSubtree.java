package org.wshuai.leetcode;

/**
 * Created by Wei on 09/06/2019.
 * #1120 https://leetcode.com/problems/maximum-average-subtree/
 */
public class MaximumAverageSubtree {

	private double max;

	// time O(n), space O(n)
	public double maximumAverageSubtree(TreeNode root) {
		max = 0.0;
		dfs(root);
		return max;
	}

	private int[] dfs(TreeNode root){
		if(root == null){
			return new int[]{0, 0};
		}
		int[] left = dfs(root.left);
		int[] right = dfs(root.right);
		int[] res = new int[]{left[0] + right[0] + root.val,
			left[1] + right[1] + 1};
		double avg = res[0] * 1.0 / res[1];
		max = Math.max(max, avg);
		return res;
	}
}
