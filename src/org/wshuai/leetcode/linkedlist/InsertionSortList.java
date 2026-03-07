package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 01/18/2020.
 * #0147 https://leetcode.com/problems/insertion-sort-list/
 */
public class InsertionSortList {

    // time O(n^2), space O(1)
    public ListNode insertionSortList(ListNode head) {
        ListNode res = new ListNode(-1);
        while (head != null) {
            ListNode next = head.next, curr = res;
            head.next = null;
            insert(head, curr);
            head = next;
        }
        return res.next;
    }

    private void insert(ListNode node, ListNode head) {
        while (head != null) {
            ListNode next = head.next;
            if (next == null || next.val > node.val) {
                head.next = node;
                node.next = next;
                break;
            }
            head = next;
        }
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
