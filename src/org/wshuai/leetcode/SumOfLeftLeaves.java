package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2016.
 * #0404 https://leetcode.com/problems/sum-of-left-leaves/
 */
public class SumOfLeftLeaves {
	// time O(n)
	public int sumOfLeftLeaves(TreeNode root) {
		if(root == null){
			return 0;
		}
		return dfs(root.left, true) + dfs(root.right, false);
	}

	private int dfs(TreeNode root, boolean isLeft){
		if(root == null){
			return 0;
		}
		if(root.left == null && root.right == null && isLeft){
			return root.val;
		}
		return dfs(root.left, true) + dfs(root.right, false);
	}
}
