package org.wshuai.leetcode;

/**
 * Created by Wei on 01/04/2020.
 * #0019 https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEndOfList {

	// time O(n), space O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 前后指针
        ListNode root = new ListNode(-1, head), // dummy 根结点
                right = root, // 前指针
                left = root; // 后指针
        // 让 right 指针先走 n 步
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        // 两个指针一起走，当 right 指向最后一个节点时， left 指向 倒数第 n + 1 个节点 -
        // 即目标节点前面一个节点。
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        // 删除操作
        left.next = left.next.next;
        return root.next;
    }

    /**
     * Definition for singly-linked list.
     */
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
}
