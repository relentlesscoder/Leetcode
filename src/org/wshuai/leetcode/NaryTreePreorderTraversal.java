package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 8/8/19.
 * #589 https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 */
public class NaryTreePreorderTraversal {
	public List<Integer> preorder(NaryTreeNode root) {
		List<Integer> lst = new ArrayList<Integer>();
		if (root == null) {
			return lst;
		}
		LinkedList<NaryTreeNode> stack = new LinkedList<NaryTreeNode>();
		stack.add(root);
		while (!stack.isEmpty()) {
			NaryTreeNode node = stack.pollFirst();
			lst.add(node.val);
			for (int i = node.children.size() - 1; i >= 0; i--) {
				stack.addFirst(node.children.get(i));
			}
		}
		return lst;
	}
}
