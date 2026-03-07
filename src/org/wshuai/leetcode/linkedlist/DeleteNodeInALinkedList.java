package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 10/26/2016.
 * #0237 https://leetcode.com/problems/delete-node-in-a-linked-list/
 */
public class DeleteNodeInALinkedList {

    // time O(1), space O(1)
    public void deleteNode(ListNode node) {
		// 脑筋急转弯: 将当前节点值改为下一个节点的值然后删掉下一个节点
        ListNode next = node.next;
        if (next == null) { // 如果下一个节点为空，则把当前节点设为空
            node = null;
        } else {
            node.val = next.val;
            node.next = next.next;
            next.next = null;
        }
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
