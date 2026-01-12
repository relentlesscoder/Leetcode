package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2025.
 * #2046 https://leetcode.com/problems/sort-linked-list-already-sorted-using-absolute-values/
 */
public class SortLinkedListAlreadySortedUsingAbsoluteValues {

    // time O(n), space O(1)
    public ListNode sortLinkedList(ListNode head) {
        ListNode curr = head.next, prev = head;
        while (curr != null) {
            ListNode next = curr.next;
            // 将绝对值小于或等于当前头节点的值插入到队首作为新的头节点
            if (curr.val <= head.val) {
                prev.next = next;
                curr.next = head;
                head = curr;
            } else {
                prev = curr;
            }
            curr = next;
        }
        return head;
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
