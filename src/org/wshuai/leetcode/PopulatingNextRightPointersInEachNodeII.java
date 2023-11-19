package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 03/07/2017.
 * #0117 https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 */
public class PopulatingNextRightPointersInEachNodeII {

	// time O(n), space O(1)
	public TreeLinkNode connect(TreeLinkNode root) {
		if(root == null){
			return null;
		}
		// dummy always pointing to the leftmost nodes of next level
		TreeLinkNode dummy = new TreeLinkNode(0);
		dummy.next = root;
		while(dummy.next != null){
			TreeLinkNode cur = dummy.next, prev = dummy;
			dummy.next = null;
			while(cur != null){
				if(cur.left != null){
					prev.next = cur.left;
					prev = prev.next;
				}
				if(cur.right != null){
					prev.next = cur.right;
					prev = prev.next;
				}
				cur = cur.next;
			}
		}
		return root;
	}

	// time O(n), space O(n)
	public TreeLinkNode connectBFS(TreeLinkNode root) {
		if(root == null){
			return null;
		}
		LinkedList<TreeLinkNode> queue = new LinkedList<>();
		queue.offerLast(root);
		while(!queue.isEmpty()){
			int size = queue.size();
			TreeLinkNode prev = null;
			while(size-- > 0){
				TreeLinkNode cur = queue.pollFirst();
				if(prev != null){
					prev.next = cur;
				}
				if(cur.left != null){
					queue.offerLast(cur.left);
				}
				if(cur.right != null){
					queue.offerLast(cur.right);
				}
				prev = cur;
			}
		}
		return root;
	}

	// Definition for a Node.
	private class TreeLinkNode {

		public int val;
		public TreeLinkNode left;
		public TreeLinkNode right;
		public TreeLinkNode next;

		public TreeLinkNode() {}

		public TreeLinkNode(int _val) {
			val = _val;
		}

		public TreeLinkNode(int _val, TreeLinkNode _left, TreeLinkNode _right, TreeLinkNode _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}
	}
}
