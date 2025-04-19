package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 04/19/2025.
 * #3217 https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/
 */
public class DeleteNodesFromLinkedListPresentInArray {

    // time O(m + n), space O(m)
    public ListNode modifiedList(int[] nums, ListNode head) {
        ListNode root = new ListNode(-1), prev = root;
        root.next = head;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        while (head != null) {
            if (set.contains(head.val)) {
                prev.next = head.next;
            } else {
                prev = head;
            }
            head = head.next;
        }
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
