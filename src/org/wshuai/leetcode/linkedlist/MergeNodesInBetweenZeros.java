package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 11/20/2023.
 * #2181 https://leetcode.com/problems/merge-nodes-in-between-zeros/
 */
public class MergeNodesInBetweenZeros {

    // time O(n), space O(n)
    public ListNode mergeNodes(ListNode head) {
        ListNode root = new ListNode(-1), tail = root;
        int sum = 0;
        while (head != null) {
            if (head.val == 0 && sum > 0) {
                tail.next = new ListNode(sum);
                tail = tail.next;
                sum = 0;
            }
            sum += head.val;
            head = head.next;
        }
        return root.next;
    }

    /**
     * Definition for singly-linked list.
     */
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
