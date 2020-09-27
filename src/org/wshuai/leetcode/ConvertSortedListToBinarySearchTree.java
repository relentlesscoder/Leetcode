package org.wshuai.leetcode;

/**
 * Created by Wei on 01/31/2016.
 * #0109 https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 */
public class ConvertSortedListToBinarySearchTree {

	private ListNode cur;

	// time O(n)
	public TreeNode sortedListToBST(ListNode head) {
		cur = head;
		ListNode node = head;
		int size = 0;
		while (node != null) {
			size++;
			node = node.next;
		}
		return inorder(0, size - 1);
	}

	private TreeNode inorder(int i, int j) {
		if (i > j) {
			return null;
		}
		int k = i + (j - i) / 2;
		TreeNode left = inorder(i, k - 1);

		TreeNode root = new TreeNode(cur.val);
		root.left = left;
		cur = cur.next;

		TreeNode right = inorder(k + 1, j);
		root.right = right;

		return root;
	}

	//Definition for singly-linked list.
	private class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	//Definition for a binary tree node.
	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
