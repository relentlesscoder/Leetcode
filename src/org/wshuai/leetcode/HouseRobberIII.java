package org.wshuai.leetcode;

/**
 * Created by Wei on 10/30/2016.
 * #0337 https://leetcode.com/problems/house-robber-iii/
 */
public class HouseRobberIII {
	// time O(n)
	// https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-tackling-of-the-problem
	public int rob(TreeNode root) {
		int[] res = dfs(root);
		return Math.max(res[0], res[1]);
	}

	private int[] dfs(TreeNode root){
		if(root == null){
			return new int[2];
		}
		int[] left = dfs(root.left);
		int[] right = dfs(root.right);
		// the first element of which denotes the maximum amount of money
		// that can be robbed if root is not robbed, while the second element
		// signifies the maximum amount of money robbed if it is robbed.
		int[] res = new int[2];
		res[0] = Math.max(left[0], left[1])
				+ Math.max(right[0], right[1]);
		res[1] = root.val + left[0] + right[0];

		return res;
	}
}
