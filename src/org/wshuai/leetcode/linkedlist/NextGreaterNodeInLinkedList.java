package org.wshuai.leetcode.linkedlist;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 09/19/2019.
 * #1019 https://leetcode.com/problems/next-greater-node-in-linked-list/
 */
public class NextGreaterNodeInLinkedList {

    // time O(n), space O(n)
    public int[] nextLargerNodes(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int n = list.size();
        int[] res = new int[n];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int x = list.get(i).val;
            while (!queue.isEmpty() && x > list.get(queue.peek()).val) {
                res[queue.poll()] = x;
            }
            queue.push(i);
        }
        return res;
    }

    /**
     * Definition for singly-linked list.
     */
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
