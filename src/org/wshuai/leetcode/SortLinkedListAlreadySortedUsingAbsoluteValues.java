package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 07/06/2025.
 * #2046 https://leetcode.com/problems/sort-linked-list-already-sorted-using-absolute-values/
 */
public class SortLinkedListAlreadySortedUsingAbsoluteValues {

    // time O(n), space O(1)
    public ListNode sortLinkedList(ListNode head) {
        ListNode root = head, prev = root, curr = root.next, next = new ListNode(-1);
        while (curr != null) {
            if (curr.val >= 0) { // add to the end if value is non-negative
                prev.next = curr;
                prev = curr;
                next = curr.next;
            } else { // add to the front if value is negative
                prev.next = null;
                next = curr.next;
                curr.next = root;
                root = curr;
            }
            curr = next;
        }
        return root;
    }

    // time O(n), space O(n)
    public ListNode sortLinkedListDeque(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode root = new ListNode(-1), curr = head;
        while (curr != null) {
            if (curr.val >= 0) {
                stack.offer(curr);
            } else {
                stack.offerFirst(curr);
            }
            curr = curr.next;
        }
        curr = root;
        while (!stack.isEmpty()) {
            stack.peekFirst().next = null;
            curr.next = stack.pollFirst();
            curr = curr.next;
        }
        return root.next;
    }

    /*Definition for singly-linked list.*/
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
}
