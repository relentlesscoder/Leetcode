package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/21/2019.
 * #919 https://leetcode.com/problems/complete-binary-tree-inserter/
 */
public class CompleteBinaryTreeInserter {

	private TreeNode root;
	// save tree nodes that missing left or right child (next insertion point)
	private LinkedList<TreeNode> parents;

	public CompleteBinaryTreeInserter(TreeNode root) {
		parents = new LinkedList<>();
		this.root = root;
		treeLevelTraversal(root);
	}

	public int insert(int v) {
		TreeNode node = new TreeNode(v);
		TreeNode parent = parents.peekFirst();
		if(parent.left == null){
			parent.left = node;
		}else{
			parent.right = node;
			parents.pollFirst();
		}
		parents.offerLast(node);
		return parent.val;
	}

	public TreeNode get_root() {
		return this.root;
	}

	private void treeLevelTraversal(TreeNode root){
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			TreeNode node = queue.pollFirst();
			if(node.left == null || node.right == null){
				parents.offerLast(node);
			}
			if(node.left != null){
				queue.offerLast(node.left);
			}
			if(node.right != null){
				queue.offerLast(node.right);
			}
		}
	}
}
