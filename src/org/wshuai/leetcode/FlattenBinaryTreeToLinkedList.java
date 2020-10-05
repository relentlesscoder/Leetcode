package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #0114 https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class FlattenBinaryTreeToLinkedList {

	private TreeNode prev = null;

	// time O(n)
	public void flatten(TreeNode root) {
		if(root == null){
			return;
		}
		flatten(root.right);
		flatten(root.left);
		root.right = prev;
		root.left = null;
		prev = root;
	}
}
