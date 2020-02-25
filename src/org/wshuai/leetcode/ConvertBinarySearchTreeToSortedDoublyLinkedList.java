package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2019.
 * #0426 https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
	private TreeNode prev = null;

	public TreeNode treeToDoublyList(TreeNode root) {
		if(root == null){
			return null;
		}
		TreeNode dummy = new TreeNode(0);
		prev = dummy;
		dfs(root);
		prev.right = dummy.right;
		dummy.right.left = prev;
		return dummy.right;
	}

	private void dfs(TreeNode cur){
		if(cur == null){
			return;
		}
		dfs(cur.left);
		prev.right = cur;
		cur.left = prev;
		prev = cur;
		dfs(cur.right);
	}
}
