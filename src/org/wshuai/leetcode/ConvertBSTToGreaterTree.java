package org.wshuai.leetcode;

/**
 * Created by Wei on 07/20/2017.
 * #0538 https://leetcode.com/problems/convert-bst-to-greater-tree/
 */
public class ConvertBSTToGreaterTree {
	private int sum;

	// time O(n)
	public TreeNode convertBST(TreeNode root) {
		if(root == null){
			return null;
		}
		sum = 0;
		dfs(root);
		return root;
	}

	private void dfs(TreeNode root){
		if(root == null){
			return;
		}
		dfs(root.right);
		root.val += sum;
		sum = root.val;
		dfs(root.left);
	}
}
