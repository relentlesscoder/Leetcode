package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 09/19/2019.
 * #1019 https://leetcode.com/problems/next-greater-node-in-linked-list/
 */
public class NextGreaterNodeInLinkedList {

    // time O(n), space O(n)
    public int[] nextLargerNodes(ListNode head) {
        ListNode curr = head;
        Map<Integer, Integer> map = new HashMap<>();
        List<ListNode> nodes = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 0; curr != null; i++, curr = curr.next) {
            while (stack.size() > 1 && nodes.get(stack.peek()).val < curr.val) {
                map.put(stack.pop(), curr.val);
            }
            stack.push(i);
            nodes.add(curr);
        }
        int[] res = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            res[i] = map.getOrDefault(i, 0);
        }
        return res;
    }

    // time O(n), space O(n)
    public int[] nextLargerNodesTwoPass(ListNode head) {
        ListNode curr = head;
        int n = 0;
        for (; curr != null; curr = curr.next) {
            n++;
        }
        curr = head;
        int[] res = new int[n];
        ListNode[] nodes = new ListNode[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 0; curr != null; i++, curr = curr.next) {
            while (stack.size() > 1 && nodes[stack.peek()].val < curr.val) {
                res[stack.pop()] = curr.val;
            }
            stack.push(i);
            nodes[i] = curr;
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
