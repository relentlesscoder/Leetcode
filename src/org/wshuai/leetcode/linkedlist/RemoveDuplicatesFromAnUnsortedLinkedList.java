package org.wshuai.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 05/22/2021.
 * #1836 https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list/
 */
public class RemoveDuplicatesFromAnUnsortedLinkedList {

    // time O(n), space O(n)
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        Map<Integer, Integer> freq = new HashMap<>();
        ListNode root = new ListNode(-1000), curr = head, prev = root;
        root.next = head;
        while (curr != null) {
            freq.merge(curr.val, 1, Integer::sum);
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            if (freq.get(curr.val) > 1) {
                prev.next = next;
                curr.next = null;
            } else {
                prev = curr;
            }
            curr = next;
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
