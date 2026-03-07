package org.wshuai.leetcode.divideandconquer;

/**
 * Created by Wei on 08/20/2016.
 * #0148 https://leetcode.com/problems/sort-list/
 */
public class SortList {

    // time O(n * log(n)), space O(log(n))
    public ListNode sortList(ListNode head) {
        // 分治层层合并相邻两个排序好的链表
        if (head == null || head.next == null) {
            return head;
        }
        ListNode head2 = getMiddle(head);
        head = sortList(head);
        head2 = sortList(head2);
        return merge(head, head2);
    }

    private ListNode merge(ListNode node1, ListNode node2) {
        ListNode root = new ListNode(0), curr = root;
        while (node1 != null || node2 != null) {
            int v1 = node1 == null ? Integer.MAX_VALUE : node1.val;
            int v2 = node2 == null ? Integer.MAX_VALUE : node2.val;
            if (v1 < v2) {
                curr.next = node1;
                node1 = node1.next;
            } else {
                curr.next = node2;
                node2 = node2.next;
            }
            curr = curr.next;
            curr.next = null;
        }
        return root.next;
    }

    private ListNode getMiddle(ListNode head) {
        ListNode fast = head, slow = head, prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = null;
        return slow;
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
