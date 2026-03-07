package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 01/22/2020.
 * #0203 https://leetcode.com/problems/remove-linked-list-elements/
 */
public class RemoveLinkedListElements {

    // time O(n), space O(1)
    public ListNode removeElements(ListNode head, int val) {
        ListNode root = new ListNode(-1), curr = head, prev = root;
        root.next = head;
        while (curr != null) {
            ListNode next = curr.next;
            // 如果值等于 val
            if (curr.val == val) {
                prev.next = next; // 将前一个节点的 next 指针指向当前节点下一个节点
                curr.next = null; // 将当前节点的 next 指针设为 null
            } else {
                prev = curr;
            }
            curr = next;
        }
        return root.next;
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
