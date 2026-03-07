package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 12/05/2020.
 * #1669 https://leetcode.com/problems/merge-in-between-linked-lists/
 */
public class MergeInBetweenLinkedLists {

    // time O(m + n), space O(1)
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode root = new ListNode(-1), curr = list1, prev = root, head = null, tail = null;
        root.next = list1;
        int cnt = 0;
        // 遍历 list1，找到两个目标节点
        while (curr != null) { // O(n)
            ListNode next = curr.next;
            // 找到 a 节点，将 a 前一个节点记录为 head - 这个点将连接 list2 的第一个节点。
            if (cnt == a) {
                head = prev;
                prev.next = null;
            }
            // 找到 b 节点，将 b 后一个节点记录为 tail - 这个点将连接 list2 的最后一个节点。
            if (cnt == b) {
                tail = curr.next;
                curr.next = null;
                break;
            }
            prev = curr;
            curr = next;
            cnt++;
        }
        // 连接 list2
        head.next = list2;
        curr = list2;
        while (curr.next != null) { // O(m)
            curr = curr.next;
        }
        curr.next = tail;
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
