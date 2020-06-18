package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 06/17/2020.
 * #1485 https://leetcode.com/problems/clone-binary-tree-with-random-pointer/
 */
public class CloneBinaryTreeWithRandomPointer {

	// time O(n), space O(n)
	public NodeCopy copyRandomBinaryTree(Node root) {
		Map<Node, NodeCopy> map = new HashMap<>();
		NodeCopy newRoot = constructTree(root, map);
		populateRandom(root, newRoot, map);
		return newRoot;
	}

	private NodeCopy constructTree(Node root, Map<Node, NodeCopy> map){
		if(root == null){
			return null;
		}
		NodeCopy newNode = new NodeCopy(root.val);
		map.put(root, newNode);
		newNode.left = constructTree(root.left, map);
		newNode.right = constructTree(root.right, map);
		return newNode;
	}

	private void populateRandom(Node node, NodeCopy newNode, Map<Node, NodeCopy> map){
		if(node == null){
			return;
		}
		if(node.random != null){
			newNode.random = map.get(node.random);
		}
		populateRandom(node.left, newNode.left, map);
		populateRandom(node.right, newNode.right, map);
	}

	private class Node {
		int val;
		Node left;
		Node right;
		Node random;
		Node() {}
		Node(int val) { this.val = val; }
		Node(int val, Node left, Node right, Node random) {
			this.val = val;
			this.left = left;
			this.right = right;
			this.random = random;
		}
	}

	private class NodeCopy {
		int val;
		NodeCopy left;
		NodeCopy right;
		NodeCopy random;
		NodeCopy() {}
		NodeCopy(int val) { this.val = val; }
		NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
			this.val = val;
			this.left = left;
			this.right = right;
			this.random = random;
		}
	}


}
