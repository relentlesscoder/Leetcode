package org.wshuai.leetcode;

/**
 * Created by Wei on 12/07/2020.
 * #1650 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
 */
public class LowestCommonAncestorOfABinaryTreeIII {

	// time O(log(n)), space O(1)
	public Node lowestCommonAncestorCyclicLinkedList(Node p, Node q) {
		// Same as #0160
		// a: 4 -> 2 -> 5 -> 3 -> n -> 8 -> 1 -> 3
		// b: 8 -> 1 -> 3 -> n -> 4 -> 2 -> 5 -> 3
		Node a = p, b = q;
		while (a != b) {
			a = a == null ? q : a.parent;
			b = b == null ? p : b.parent;
		}
		return a;
	}

	// Definition for a Node.
	static class Node {
		public int val;
		public Node left;
		public Node right;
		public Node parent;
	}
}
