package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 10/26/2016.
 * #0206 https://leetcode.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {

    // time O(n), space O(1)
    public ListNode reverseList(ListNode head) {
        ListNode curr = head, prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev; // 反转相邻两节点
            prev = curr;
            curr = next;
        }
        return prev;
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
