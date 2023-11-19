package org.wshuai.leetcode;

/**
 * Created by Wei on 01/13/2021.
 * #1721 https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
 */
public class SwappingNodesInALinkedList {

    // time O(n)
    public ListNode swapNodes(ListNode head, int k) {
        ListNode fast = head, slow = null, p1 = null;
        for (int i = 1; fast != null; i++) {
            if (i == k) {
                p1 = fast;
                slow = head;
            }
            if (fast.next == null) {
                break;
            }
            fast = fast.next;
            if (slow != null) {
                slow = slow.next;
            }
        }
        int val = slow.val;
        slow.val = p1.val;
        p1.val = val;
        return head;
    }

    // Definition for singly-linked list.
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
