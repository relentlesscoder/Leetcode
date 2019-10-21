package org.wshuai.leetcode;

/**
 * Created by Wei on 10/14/2019.
 * #998 https://leetcode.com/problems/maximum-binary-tree-ii/
 */
public class MaximumBinaryTreeII {
	public TreeNode insertIntoMaxTree(TreeNode root, int val) {
		TreeNode prev = null;
		TreeNode curr = root;
		while(curr != null && curr.val > val){
			prev = curr;
			curr = curr.right;
		}
		TreeNode node = new TreeNode(val);
		node.left = curr;
		if(prev == null){
			return node;
		}
		prev.right = node;
		return root;
	}
}
