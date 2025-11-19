package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 01/19/2016.
 * #0094 https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class BinaryTreeInorderTraversal {

	// time O(n), space O(1)
	public List<Integer> inorderTraversalMorris(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		TreeNode cur = root, pre = null;
		while (cur != null) {
			if (cur.left == null) {
				// If there is no left subtree, goes to the right
				// subtree or back to predecessor via the dummy link
				res.add(cur.val);
				cur = cur.right;
			} else {
				// Find the rightmost child for the left subtree of
				// the current node
				pre = cur.left;
				while (pre.right != null && pre.right != cur) {
					pre = pre.right;
				}
				// Add a dummy link to link right of prev to cur
				if (pre.right == null) {
					pre.right = cur;
					// Continue traverse the left subtree
					cur = cur.left;
				} else {
					// If right pointer is already set
					// Reset the right pointer
					pre.right = null;
					// Left subtree traversal is finished
					res.add(cur.val);
					// Go to the right subtree
					cur = cur.right;
				}
			}
		}
		return res;
	}

	// time O(n), space O(n)
	public List<Integer> inorderTraversalIterative(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode node = root;
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				TreeNode parent = stack.pop();
				res.add(parent.val);
				node = parent.right;
			}
		}
		return res;
	}

	// time O(n), space a: O(log(n)) w: O(n)
	public List<Integer> inorderTraversalRecursive(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		inorder(root, res);
		return res;
	}

	private void inorder(TreeNode node, List<Integer> res) {
		if (node == null) {
			return;
		}
		inorder(node.left, res);
		res.add(node.val);
		inorder(node.right, res);
	}
}
