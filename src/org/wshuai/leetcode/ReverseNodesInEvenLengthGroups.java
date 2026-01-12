package org.wshuai.leetcode;

/**
 * Created by Wei on 09/21/2025.
 * #2074 https://leetcode.com/problems/reverse-nodes-in-even-length-groups/
 */
public class ReverseNodesInEvenLengthGroups {

    // time O(n), space O(1)
    public ListNode reverseEvenLengthGroups(ListNode head) {
        // #0025 类似题
        // 注意题目要求是将偶数长度的组中的节点
        ListNode root = new ListNode(-1, head), // dummy 根结点
                curr = head, // 当前节点
                prev = null, // 前一个节点
                last = null; // 上一组的尾节点
        int k = 1;
        while (curr != null) {
            int i = 0;
            // 根据 k 的值来遍历当前节点组。
            for (; i < k && curr != null; i++) {
                prev = curr;
                curr = curr.next;
            }
            // 当前组里的节点总数是 i 。
            if (i % 2 == 0) {
                // 如果是偶数，反转数组。
                last = reverse(last, curr);
            } else {
                last = prev;
            }
            k++;
        }
        return root.next;
    }

    private ListNode reverse(ListNode root, ListNode end) {
        ListNode curr = root.next, prev = null;
        while (curr != end) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        ListNode nxt = root.next;
        root.next.next = curr;
        root.next = prev;
        return nxt;
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
