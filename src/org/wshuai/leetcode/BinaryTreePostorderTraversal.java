package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 1/20/16.
 * #145 https://leetcode.com/problems/binary-tree-postorder-traversal/
 */
public class BinaryTreePostorderTraversal {
	public static List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();

		if (root == null) {
			return result;
		}

		Stack<TreeNode> stack = new Stack<TreeNode>();

		stack.push(root);
		TreeNode prev = null;
		while (!stack.isEmpty()) {
			TreeNode current = stack.peek();
			/* go down the tree in search of a leaf an if so process it
            and pop stack otherwise move down */
			if (prev == null || prev.left == current || prev.right == current) {
				if (current.left != null) {
					stack.push(current.left);
				} else if (current.right != null) {
					stack.push(current.right);
				} else {
					result.add(current.val);
				}
				/* go up the tree from left node, if the child is right
                   push it onto stack otherwise process parent and pop
                   stack */
			} else if (current.left == prev) {
				if (current.right != null) {
					stack.push(current.right);
				} else {
					result.add(current.val);
				}
				/* go up the tree from right node and after coming back
                 from right node process parent and pop stack */
			} else if (current.right == prev) {
				result.add(current.val);
			} else {
				stack.pop();
			}

			prev = current;
		}
		return result;
	}
}
