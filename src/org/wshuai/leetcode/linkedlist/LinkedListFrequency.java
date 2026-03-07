package org.wshuai.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/10/2026.
 * #3063 https://leetcode.com/problems/linked-list-frequency/
 */
public class LinkedListFrequency {

    // time O(n), space O(k)
    public ListNode frequenciesOfElements(ListNode head) {
        ListNode root = new ListNode(-1), curr = root;
        Map<Integer, Integer> freq = new HashMap<>();
        while (head != null) {
            freq.merge(head.val, 1, Integer::sum);
            head = head.next;
        }
        for (int f : freq.values()) {
            curr.next = new ListNode(f);
            curr = curr.next;
        }
        return root.next;
    }

    /**
     * Definition for singly-linked list.
     **/
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
