package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 1/26/16.
 * #173 https://leetcode.com/problems/binary-search-tree-iterator/
 * This problem is just another way of implement inorder iterative traversal
 * http://articles.leetcode.com/2010/04/binary-search-tree-in-order-traversal.html
 */
public class BinarySearchTreeIterator {

	private Stack<TreeNode> stack;
	TreeNode current;
	TreeNode rootNode;

	public BinarySearchTreeIterator(TreeNode root) {
		stack = new Stack<TreeNode>();
		current = root;
		rootNode = root;
	}

	/**
	 * @return whether we have a next smallest number
	 */
	public boolean hasNext() {
		if (rootNode == null) {
			return false;
		}

		return (!stack.isEmpty() || current != null);
	}

	/**
	 * @return the next smallest number
	 */
	public int next() {
		while (!stack.isEmpty() || current != null) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else {
				TreeNode next = stack.peek();
				current = stack.pop();
				current = current.right;
				return next.val;
			}
		}

		return 0;
	}
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
