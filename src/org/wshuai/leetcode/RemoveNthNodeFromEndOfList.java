package org.wshuai.leetcode;

/**
 * Created by Wei on 01/04/2020.
 * #0019 https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEndOfList {

	// time O(n)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode root = new ListNode(0), fast = head, slow = head, prev = root;
        root.next = head;
        int i = 0;
        while (i++ < n) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = slow.next;
        return root.next;
    }

    // Definition for singly-linked list.
    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}


