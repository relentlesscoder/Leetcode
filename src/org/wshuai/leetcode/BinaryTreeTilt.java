package org.wshuai.leetcode;

/**
 * Created by Wei on 07/24/2017.
 * #0563 https://leetcode.com/problems/binary-tree-tilt/
 */
public class BinaryTreeTilt {
	private int res;

	// time O(n)
	public int findTilt(TreeNode root) {
		res = 0;
		if(root == null){
			return res;
		}
		dfs(root);
		return res;
	}

	private int dfs(TreeNode root){
		if(root == null){
			return 0;
		}
		int left = dfs(root.left);
		int right = dfs(root.right);
		res += Math.abs(left - right);
		return root.val + left + right;
	}
}
