package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0143 https://leetcode.com/problems/reorder-list/
 */
public class ReorderList {

    // time O(n), space O(1)
    public void reorderList(ListNode head) {
		// #0234 类似题
		// 快慢指针找到中间节点
        ListNode middle = getMiddle(head);
		// 反转链表的后半部分
        ListNode head2 = reverse(middle);
		// 合并前后部分
        while (head2 != null && head != head2 && head.next != head2) {
            ListNode next = head.next, next2 = head2.next;
            head.next = head2;
            head2.next = next;
            head = next;
            head2 = next2;
        }
    }

    private ListNode getMiddle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
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
