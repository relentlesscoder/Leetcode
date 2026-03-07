package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 01/13/2021.
 * #1721 https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
 */
public class SwappingNodesInALinkedList {

    // time O(n), space O(1)
    public ListNode swapNodes(ListNode head, int k) {
        ListNode root = new ListNode(-1, head), // dummy 根结点
                first = root, // 正数第 k 个节点
                right = root, // 前指针
                left = root; // 后指针
        while (k-- > 0) {
            first = first.next; // 找到正数第 k 个节点
            right = right.next; // 前指针先走 k 步
        }
        while (right != null) { // 前后指针一起走，走完 left 指向倒数第 k 个节点
            right = right.next;
            left = left.next;
        }
        // 交换两个节点的值
        int val = first.val;
        first.val = left.val;
        left.val = val;
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
