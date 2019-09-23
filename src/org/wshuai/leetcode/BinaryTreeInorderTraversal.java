package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 1/19/2016.
 * #94 https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class BinaryTreeInorderTraversal {

	//O(n), iterative
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current = root;
		while (current != null || !stack.empty()) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else {
				TreeNode parent = stack.pop();
				result.add(parent.val);
				current = parent.right;
			}
		}
		return result;
	}

}
