package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 01/05/2020.
 * #0024 https://leetcode.com/problems/swap-nodes-in-pairs/
 */
public class SwapNodesInPairs {

    // time O(n), space O(1)
    public ListNode swapPairs(ListNode head) {
        // 两个节点为一组遍历链表
        ListNode root = new ListNode(-1, head), // dummy 根节点
                curr = head, // 当前组的第一个节点
                prev = root; // 前一组的最后一个节点
        while (curr != null && curr.next != null) {
            ListNode next = curr.next.next; // 记录下一组的第一个节点
            curr.next.next = curr; // 反转当前组内两节点
            prev.next = curr.next; // 将前一个节点的 next 指针指向当前组内第二个节点
            curr.next = next; // 将当前组内第一个节点的 next 指针指向下一组的第一个节点
            prev = curr;
            curr = next; // 指向下一组的第一个节点
        }
        return root.next;
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
