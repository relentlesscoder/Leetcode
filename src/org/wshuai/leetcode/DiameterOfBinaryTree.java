package org.wshuai.leetcode;

/**
 * Created by Wei on 04/03/2017.
 * #0543 https://leetcode.com/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTree {

	private int res;

	// time O(n)
	public int diameterOfBinaryTree(TreeNode root) {
		if(root == null){
			return 0;
		}
		res = 0;
		dfs(root);
		return res;
	}

	private int dfs(TreeNode root){
		if(root == null){
			return 0;
		}
		int left = dfs(root.left);
		int right = dfs(root.right);
		res = Math.max(res, left + right);
		return 1 + Math.max(left, right);
	}
}
