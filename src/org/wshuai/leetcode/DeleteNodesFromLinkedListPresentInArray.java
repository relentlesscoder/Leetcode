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
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }
        // 同 #0203
        ListNode root = new ListNode(-1), curr = head, prev = root;
        root.next = head;
        while (curr != null) {
            ListNode next = curr.next;
            if (set.contains(curr.val)) {
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
