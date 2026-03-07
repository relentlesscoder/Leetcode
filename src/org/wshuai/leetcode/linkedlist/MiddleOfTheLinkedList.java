package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 08/09/2019.
 * #0876 https://leetcode.com/problems/middle-of-the-linked-list/
 */
public class MiddleOfTheLinkedList {

    // time O(n), space O(1)
    public ListNode middleNode(ListNode head) {
        ListNode fast = head, // 快指针
                slow = head; // 慢指针
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
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
