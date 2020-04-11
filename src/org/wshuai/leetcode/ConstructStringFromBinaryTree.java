package org.wshuai.leetcode;

/**
 * Created by Wei on 07/20/2017.
 * #0606 https://leetcode.com/problems/construct-string-from-binary-tree/
 */
public class ConstructStringFromBinaryTree {
	// time O(n)
	public String tree2str(TreeNode t) {
		StringBuilder res = new StringBuilder();
		dfs(t, res);
		return res.toString();
	}

	private void dfs(TreeNode root, StringBuilder res){
		if(root == null){
			return;
		}
		res.append("" + root.val);
		if(root.left == null && root.right == null){
			return;
		}
		res.append("(");
		dfs(root.left, res);
		res.append(")");
		if(root.right != null){
			res.append("(");
			dfs(root.right, res);
			res.append(")");
		}
	}
}
