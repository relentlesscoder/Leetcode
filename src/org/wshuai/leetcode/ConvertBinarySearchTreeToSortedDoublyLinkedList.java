package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/04/2019.
 * #0426 https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

	private TreeNode prev = null;

	// time O(n)
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

	// time O(n), space O(n)
	public TreeNode treeToDoublyListStack(TreeNode root) {
		if(root == null){
			return null;
		}
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root, dummy = new TreeNode(0), prev = dummy;
		while(!stack.isEmpty() || cur != null){
			if(cur != null){
				stack.push(cur);
				cur = cur.left;
			}else{
				TreeNode parent = stack.pop();
				prev.right = parent;
				parent.left = prev;
				prev = parent;
				cur = parent.right;
			}
		}
		dummy.right.left = prev;
		prev.right = dummy.right;
		return dummy.right;
	}
}
