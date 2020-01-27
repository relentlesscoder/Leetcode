package org.wshuai.leetcode;

/**
 * Created by Wei on 11/13/2016.
 * #0250 https://leetcode.com/problems/count-univalue-subtrees/
 */
public class CountUnivalueSubtrees {
	private int res;

	// time O(n)
	public int countUnivalSubtrees(TreeNode root) {
		res = 0;
		dfs(root);
		return res;
	}

	private boolean dfs(TreeNode root){
		if(root == null){
			return true;
		}
		boolean left = dfs(root.left);
		boolean right = dfs(root.right);
		if(left && right
				&& (root.left == null || root.val == root.left.val)
				&& (root.right == null || root.val == root.right.val)){
			res++;
			return true;
		}
		return false;
	}
}
