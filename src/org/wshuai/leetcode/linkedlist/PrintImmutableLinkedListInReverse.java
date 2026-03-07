package org.wshuai.leetcode.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/24/2019.
 * #1265 https://leetcode.com/problems/print-immutable-linked-list-in-reverse/
 */
public class PrintImmutableLinkedListInReverse {

    // time O(n), space O(n)
    public void printLinkedListInReverseRecursive(ImmutableListNode head) {
        if (head == null) {
            return;
        }
        printLinkedListInReverseRecursive(head.getNext());
        head.printValue();
    }

    // time O(n), space O(n)
    public void printLinkedListInReverseStack(ImmutableListNode head) {
        Deque<ImmutableListNode> queue = new ArrayDeque<>();
        for (ImmutableListNode curr = head; curr != null; curr = curr.getNext()) {
            queue.push(curr);
        }
        while (!queue.isEmpty()) {
            queue.pop().printValue();
        }
    }

    /**
     * This is the ImmutableListNode's API interface.
     * You should not implement it, or speculate about its implementation.
     */
    private interface ImmutableListNode {
        public void printValue(); // print the value of this node.

        public ImmutableListNode getNext(); // return the next node.
    }
}
