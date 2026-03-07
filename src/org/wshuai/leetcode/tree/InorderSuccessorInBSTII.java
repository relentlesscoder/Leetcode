package org.wshuai.leetcode.tree;

/**
 * Created by Wei on 10/20/2019.
 * #0510 https://leetcode.com/problems/inorder-successor-in-bst-ii/
 */
public class InorderSuccessorInBSTII {

	// time O(n), space O(1)
	public Node inorderSuccessor(Node node) {
		Node res = null;
		// 如果 node 有右子树，则后继节点为右子树的最小值
		if (node.right != null) {
			res = node.right;
			while (res != null && res.left != null) {
				res = res.left;
			}
		} else {
			// 如果 node 没有右子树，则后继节点第一个大于它的祖先节点
			res = node.parent;
			while (res != null && res.val <= node.val) {
				res = res.parent;
			}
		}
		return res;
	}

	// time O(n), space O(n)
	public Node inorderSuccessorFromRoot(Node node) {
		// 先找到根结点，再用 #0285 的方法找后继节点
		Node res = null, root = node;
		while (root.parent != null) {
			root = root.parent;
		}
		while (root != null) {
			if (root.val > node.val) {
				res = root;
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return res;
	}

	private class Node {
		public int val;

		public Node left;

		public Node right;

		public Node parent;

	}
}
