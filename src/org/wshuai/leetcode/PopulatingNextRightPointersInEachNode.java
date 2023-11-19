package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/21/2016.
 * #0116 https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 */
public class PopulatingNextRightPointersInEachNode {

	// time O(n)
	public TreeLinkNode connect(TreeLinkNode root) {
		if(root == null || root.left == null){
			return root;
		}
		root.left.next = root.right;
		root.right.next = root.next != null ? root.next.left : null;
		connect(root.left);
		connect(root.right);
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
