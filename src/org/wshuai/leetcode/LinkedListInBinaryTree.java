package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 03/01/2020.
 * #1367 https://leetcode.com/problems/linked-list-in-binary-tree/
 */
public class LinkedListInBinaryTree {
	// time O(n), space O(n)
	public boolean isSubPath(LinkedListNode head, TreeNode root) {
		LinkedList<TreeNode> queue = new LinkedList<>();
		buildQueue(root, head.val, queue);
		LinkedListNode nextListNode = head.next;
		while(!queue.isEmpty()){
			if(nextListNode == null){
				return true;
			}
			int size = queue.size();
			while(size-- > 0){
				TreeNode curTreeNode = queue.pollFirst();
				if(curTreeNode.left != null && curTreeNode.left.val == nextListNode.val){
					queue.offerLast(curTreeNode.left);
				}
				if(curTreeNode.right != null && curTreeNode.right.val == nextListNode.val){
					queue.offerLast(curTreeNode.right);
				}
			}
			nextListNode = nextListNode.next;
		}
		return false;
	}

	private void buildQueue(TreeNode root, int val, LinkedList<TreeNode> queue){
		if(root == null){
			return;
		}
		if(root.val == val){
			queue.offerLast(root);
		}
		buildQueue(root.left, val, queue);
		buildQueue(root.right, val, queue);
	}
}
