package org.wshuai.leetcode;

/**
 * Created by Wei on 11/08/2016.
 * #0086 https://leetcode.com/problems/partition-list/
 */
public class PartitionList {

    // time O(n), space O(1)
    public ListNode partition(ListNode head, int x) {
		// 同 #0328
        ListNode r1 = new ListNode(-1),
				r2 = new ListNode(-1),
				less = r1,
				ge = r2;
        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                ge.next = head;
                ge = ge.next;
            }
            head = next;
        }
        less.next = r2.next;
        return r1.next;
    }

    /**
     * Definition for singly-linked list.
     **/
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
