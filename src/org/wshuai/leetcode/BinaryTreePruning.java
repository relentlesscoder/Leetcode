package org.wshuai.leetcode;

/**
 * Created by Wei on 08/31/2019.
 * #0814 https://leetcode.com/problems/binary-tree-pruning/
 */
public class BinaryTreePruning {

	// time O(n)
	public TreeNode pruneTree(TreeNode root) {
		if(root == null){
			return null;
		}
		return dfs(root) ? root : null;
	}

	private boolean dfs(TreeNode root){
		if(root == null){
			return false;
		}
		boolean left = dfs(root.left);
		boolean right = dfs(root.right);
		if(!left){
			root.left = null;
		}
		if(!right){
			root.right = null;
		}
		return left || right || root.val == 1;
	}
}
