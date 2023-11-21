package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/2023.
 * #2807 https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/
 */
public class InsertGreatestCommonDivisorsInLinkedList {

    // time O(n * log(m)), space O(log(m))
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode root = new ListNode(-1), curr = head;
        root.next = head;
        while (curr != null && curr.next != null) {
            ListNode next = curr.next, middle = new ListNode(gcd(curr.val, next.val));
            curr.next = middle;
            middle.next = next;
            curr = next;
        }
        return root.next;
    }

    private int gcd(int x, int y){
        return x == 0 ? y : gcd(y % x, x);
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
