package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 11/20/2023.
 * #2807 https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/
 */
public class InsertGreatestCommonDivisorsInLinkedList {

    // time O(n * log(MAX)), space O(1)
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            if (next != null) {
                ListNode insert = new ListNode(gcd(curr.val, next.val));
                curr.next = insert;
                insert.next = next;
            }
            curr = next;
        }
        return head;
    }

    private int gcd(int a, int b) {
        while (a > 0) {
            int temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }

    /*
    private int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }*/

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
