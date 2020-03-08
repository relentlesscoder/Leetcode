package org.wshuai.leetcode;

/**
 * Created by Wei on 10/15/2019.
 * #0450 https://leetcode.com/problems/delete-node-in-a-bst/
 */
public class DeleteNodeInABST {
	// time O(log(n))
	// book CLRS p297
	public TreeNode deleteNode(TreeNode root, int key) {
		TreeNode dummy = new TreeNode(0), parent = dummy, cur = root;
		dummy.left = root;
		// search for node
		while(cur != null){
			if(cur.val == key){
				break;
			}
			parent = cur;
			if(cur.val > key){
				cur = cur.left;
			}else{
				cur = cur.right;
			}
		}
		// delete node
		if(cur != null){
			if(cur.left == null){
				replace(cur, cur.right, parent);
			}else if(cur.right == null){
				replace(cur, cur.left, parent);
			}else{
				// find successor
				TreeNode prev = null, successor = cur.right;
				while(successor.left != null){
					prev = successor;
					successor = successor.left;
				}
				// update value of node with that of successor
				cur.val = successor.val;
				if(successor == cur.right){
					// if successor is the right child of the node,
					// set node's right to successor's right
					cur.right = successor.right;
				}else{
					// otherwise, replace successor with it's right child
					replace(successor, successor.right, prev);
				}
			}
		}
		return dummy.left;
	}

	private void replace(TreeNode original, TreeNode replace, TreeNode parent){
		if(parent.left == original){
			parent.left = replace;
		}else{
			parent.right = replace;
		}
	}
}
