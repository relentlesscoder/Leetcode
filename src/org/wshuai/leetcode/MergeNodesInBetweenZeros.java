package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/2023.
 * #2181 https://leetcode.com/problems/merge-nodes-in-between-zeros/
 */
public class MergeNodesInBetweenZeros {

    // time O(n), space O(n)
    public ListNode mergeNodes(ListNode head) {
        int sum = 0;
        ListNode prev = new ListNode(0), root = prev, curr = head.next;
        while (curr != null) {
            if (curr.val == 0) {
                prev.next = new ListNode(sum);
                prev = prev.next;
                sum = 0;
            } else {
                sum += curr.val;
            }
            curr = curr.next;
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
