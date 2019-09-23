package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 1/19/2016.
 * #104 https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class MaximumDepthOfBinaryTree {
	public static int maxDepthRecursive(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(maxDepthRecursive(root.left) + 1, maxDepthRecursive(root.right) + 1);
	}

	public static int maxDepthIterative(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int result = 0;
		Stack<TreeNode> stack = new Stack<TreeNode>();

		TreeNode prev = null;
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode current = stack.peek();
			if (prev == null || prev.left == current || prev.right == current) {
				if (current.left != null) {
					stack.push(current.left);
				} else if (current.right != null) {
					stack.push(current.right);
				}
			} else if (current.left == prev) {
				if (current.right != null) {
					stack.push(current.right);
				}
			} else {
				stack.pop();
			}
			prev = current;

			result = Math.max(stack.size(), result);
		}

		return result;
	}
}
