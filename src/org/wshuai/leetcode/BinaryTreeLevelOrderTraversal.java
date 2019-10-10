package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Wei on 1/26/16.
 * #102 https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class BinaryTreeLevelOrderTraversal {
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> lst = new ArrayList<List<Integer>>();
		if (root == null) {
			return lst;
		}
		Queue<TreeNode> curr = new LinkedList<TreeNode>();
		Queue<TreeNode> next = new LinkedList<TreeNode>();
		List<Integer> vals = new ArrayList<Integer>();
		curr.offer(root);
		while (!curr.isEmpty()) {
			TreeNode node = curr.poll();
			if (node.left != null) {
				next.offer(node.left);
			}
			if (node.right != null) {
				next.offer(node.right);
			}
			vals.add(node.val);
			if (curr.isEmpty()) {
				curr = next;
				next = new LinkedList<TreeNode>();
				lst.add(vals);
				vals = new ArrayList<Integer>();
			}
		}

		return lst;
	}
}
