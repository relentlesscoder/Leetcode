package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2016.
 * #0114 https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class FlattenBinaryTreeToLinkedList {

	private TreeNode head = null;

	// time O(n), space O(1)
	public void flatten(TreeNode root) {
		if (root == null) {
			return;
		}
		flatten(root.right);
		flatten(root.left);
		root.left = null;
		root.right = head; // 头插法，相当于链表的 root.next = head
		head = root; // 现在链表头节点是 root
	}

	// time O(n), space O(n)
	public void flattenDFS(TreeNode root) {
		dfs(root);
	}

	private TreeNode dfs(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode leftTail = dfs(root.left), rightTail = dfs(root.right);
		if (leftTail != null) {
			leftTail.right = root.right; // 左尾节点右指针指向右节点
			root.right = root.left; // 当前节点的右指针指向左头节点
			root.left = null; // 将当前节点的左指针设为空
		}
		// 返回右尾节点，如果没有则返回左尾节点
		return rightTail != null ? rightTail : leftTail != null ? leftTail : root;
	}

	/**
     * Definition for a binary tree node.
     */
    private static class TreeNode {
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
