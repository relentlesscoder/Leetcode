package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 12/05/2020.
 * #1660 https://leetcode.com/problems/correct-a-binary-tree/
 */
public class CorrectABinaryTree {

	// time O(n)
	public TreeNode correctBinaryTree(TreeNode root) {
		Set<Integer> visited = new HashSet<>();
		// find the invalid node
		TreeNode invalid = find(root, visited);
		// remove the invalid node
		remove(root, invalid);
		return root;
	}

	private void remove(TreeNode node, TreeNode target){
		if(node == null){
			return;
		}
		if(node.left == target){
			node.left = null;
			return;
		}
		if(node.right == target){
			node.right = null;
			return;
		}
		remove(node.left, target);
		remove(node.right, target);
	}

	private TreeNode find(TreeNode node, Set<Integer> visited){
		if(node == null){
			return null;
		}
		// dfs from right to left, the invalid node's right child
		// should be already visited
		if(node.right != null && visited.contains(node.right.val)){
			return node;
		}
		visited.add(node.val);
		TreeNode right = find(node.right, visited);
		if(right != null){
			return right;
		}
		return find(node.left, visited);
	}

	// time O(n), space O(n)
	public TreeNode correctBinaryTreeLevelTraversal(TreeNode root) {
		Map<TreeNode, TreeNode> cur = new HashMap<>(), next = new HashMap<>();
		// node -> parent mapping
		cur.put(root, null);
		while(cur.size() != 0){
			for(TreeNode node : cur.keySet()){
				if(cur.containsKey(node.right)){
					TreeNode parent = cur.get(node);
					if(parent.left == node){
						parent.left = null;
					}
					if(parent.right == node){
						parent.right = null;
					}
					return root;
				}
				if(node.left != null){
					next.put(node.left, node);
				}
				if(node.right != null){
					next.put(node.right, node);
				}
			}
			cur = next;
			next = new HashMap<>();
		}
		return root;
	}
}
