package org.wshuai.leetcode;

/**
 * Created by Wei on 01/22/2020.
 * #0203 https://leetcode.com/problems/remove-linked-list-elements/
 */
public class RemoveLinkedListElements {

    // time O(n)
    public ListNode removeElements(ListNode head, int val) {
        ListNode root = new ListNode(0), cur = head, prev = root;
        root.next = head;
        while (cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return root.next;
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
