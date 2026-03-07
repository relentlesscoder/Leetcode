package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 11/02/2016.
 * #0092 https://leetcode.com/problems/reverse-linked-list-ii/
 */
public class ReverseLinkedListII {

    // time O(n), space O(1)
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left >= right) {
            return head;
        }
        ListNode root = new ListNode(-1, head), // dummy 根节点
                curr = head, // 当前节点
                prev = root, // 前一个节点
                first = null, // 编号为 left 的节点
                end = null; // first 节点的前一个节点
        int id = 0; // 节点的编号
        while (curr != null) {
            id++;
            ListNode next = curr.next;
            if (id == left) {
                end = prev; // 标记 end 节点
                first = curr; // 标记 first 节点
            } else if (id > left && id <= right) {
                curr.next = prev; // 反转相邻两节点
                if (id == right) {
                    first.next = next; // 将 first 节点的 next 指针指向当前节点的下一个节点
                    end.next = curr; // 将 end 节点的 next 指针指向当前节点
                    break; // 结束遍历
                }
            }
            prev = curr;
            curr = next;
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
