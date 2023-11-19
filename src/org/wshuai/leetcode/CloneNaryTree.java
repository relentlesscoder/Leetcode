package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 06/28/2020.
 * #1490 https://leetcode.com/problems/clone-n-ary-tree/
 */
public class CloneNaryTree {

	// time O(n)
	public Node cloneTree(Node root) {
		if (root == null) {
			return null;
		}
		Node copy = new Node(root.val);
		if (root.children != null) {
			copy.children = new ArrayList<Node>();
			for (Node c : root.children) {
				copy.children.add(cloneTree(c));
			}
		}
		return copy;
	}


	// Definition for a Node.
	private class Node {
		public int val;
		public List<Node> children;


		public Node() {
			children = new ArrayList<Node>();
		}

		public Node(int _val) {
			val = _val;
			children = new ArrayList<Node>();
		}

		public Node(int _val, ArrayList<Node> _children) {
			val = _val;
			children = _children;
		}
	}
}
