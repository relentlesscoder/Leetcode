package org.wshuai.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 03/01/2020.
 * #1367 https://leetcode.com/problems/linked-list-in-binary-tree/
 */
public class LinkedListInBinaryTree {

    // time O(m * n), space O(n)
    public boolean isSubPath(ListNode head, TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
		// DFS 找到所有值等于 head 的节点
        dfs(root, head.val, queue);
		// BFS 一个节点一个节点匹配
        while (head.next != null) {
            int size = queue.size();
            if (size == 0) {
                return false;
            }
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null && node.left.val == head.next.val) {
                    queue.offer(node.left);
                }
                if (node.right != null && node.right.val == head.next.val) {
                    queue.offer(node.right);
                }
            }
            head = head.next;
        }
        return !queue.isEmpty();
    }

    private void dfs(TreeNode root, int target, Deque<TreeNode> queue) {
        if (root == null) {
            return;
        }
        if (root.val == target) {
            queue.offer(root);
        }
        dfs(root.left, target, queue);
        dfs(root.right, target, queue);
    }

    /**
     * Definition for singly-linked list.
     */
    private static class ListNode {
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
