package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 06/28/2020.
 * #1490 https://leetcode.com/problems/clone-n-ary-tree/
 */
public class CloneNaryTree {

	// time O(n), space O(n)
	public Node cloneTree(Node root) {
		if (root == null) {
			return null;
		}
		Node newRoot = new Node(root.val);
		dfs(root, newRoot);
		return newRoot;
	}

	private void dfs(Node root, Node copyRoot) {
		if (root == null) {
			return;
		}
		List<Node> copyList = new ArrayList<>();
		for (Node child : root.children) {
			Node copyChild = new Node(child.val);
			copyList.add(copyChild);
			dfs(child, copyChild);
		}
		copyRoot.children = copyList;
	}

	/**
	 * Definition for a Node.
	 */
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
