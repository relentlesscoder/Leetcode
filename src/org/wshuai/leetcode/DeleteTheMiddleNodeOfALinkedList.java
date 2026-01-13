package org.wshuai.leetcode;

/**
 * Created by Wei on 09/26/2023.
 * #2095 https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 */
public class DeleteTheMiddleNodeOfALinkedList {

    // time O(n), space O(1)
    public ListNode deleteMiddle(ListNode head) {
        ListNode root = new ListNode(-1, head), // dummy 根结点
                fast = head, // 快指针
                slow = head, // 慢指针
                prev = root; // 前一个节点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = slow.next;
        return root.next;
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
