package org.wshuai.leetcode;

/**
 * Created by Wei on 12/14/2020.
 * #1666 https://leetcode.com/problems/change-the-root-of-a-binary-tree/
 */
public class ChangeTheRootOfABinaryTree {

	// time O(n)
	public Node flipBinaryTree(Node root, Node leaf) {
		Node cur = leaf;
		while(cur != root){
			Node parent = cur.parent;
			cur.parent = null;
			if(parent.left == cur){
				parent.left = null;
			}else{
				parent.right = null;
			}
			if(cur.left != null){
				cur.right = cur.left;
			}
			cur.left = parent;
			cur = parent;
		}
		setParent(leaf);
		return leaf;
	}

	private void setParent(Node root){
		if(root == null){
			return;
		}
		if(root.left != null){
			root.left.parent = root;
		}
		if(root.right != null){
			root.right.parent = root;
		}
		setParent(root.left);
		setParent(root.right);
	}

	// Definition for a Node.
	private class Node {
		public int val;
		public Node left;
		public Node right;
		public Node parent;
	};
}
