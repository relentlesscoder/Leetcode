package org.wshuai.leetcode;

/**
 * Created by Wei on 09/18/2023.
 * #2130 https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
 */
public class MaximumTwinSumOfALinkedList {

    // time O(n), space O(1)
    public int pairSum(ListNode head) {
        ListNode fast = head, slow = head, prev = null, curr = head;
        while (fast != null) { // use fast and slow nodes to find the start index of the second half
            fast = fast.next.next;
            slow = slow.next;
        }
        while (curr != slow) { // revert the fist half
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        int max = Integer.MIN_VALUE;
        while (slow != null) {
            max = Math.max(max, slow.val + prev.val);
            slow = slow.next;
            prev = prev.next;
        }
        return max;
    }

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
