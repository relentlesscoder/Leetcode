package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #0114 https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class FlattenBinaryTreeToLinkedList {
	// time O(n)
	public void flatten(TreeNode root) {
		if(root == null){
			return;
		}
		flatten(root.left);
		flatten(root.right);
		TreeNode temp = root.right;
		if(root.left != null){
			root.right = root.left;
			root.left = null;
			while(root.right != null){
				root = root.right;
			}
		}
		root.right = temp;
	}
}
