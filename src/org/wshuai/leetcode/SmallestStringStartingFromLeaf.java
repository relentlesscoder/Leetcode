package org.wshuai.leetcode;

/**
 * Created by Wei on 10/28/2019.
 * #988 https://leetcode.com/problems/smallest-string-starting-from-leaf/
 */
public class SmallestStringStartingFromLeaf {
	String res = "~";

	public String smallestFromLeaf(TreeNode root) {
		dfs(root, "");
		return res;
	}

	private void dfs(TreeNode node, String postFix){
		char c = toChar(node.val);
		String val = c + postFix;
		if(node.left == null && node.right == null){
			res = res.compareTo(val) < 0 ? res : val;
			return;
		}
		if(node.left != null){
			dfs(node.left, val);
		}
		if(node.right != null){
			dfs(node.right, val);
		}
	}

	private char toChar(int val){
		return (char)(val + 'a');
	}
}
