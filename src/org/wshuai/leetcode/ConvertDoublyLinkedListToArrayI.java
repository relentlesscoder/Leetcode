package org.wshuai.leetcode;

/**
 * Created by Wei on 06/22/2025.
 * #3263 https://leetcode.com/problems/convert-doubly-linked-list-to-array-i/
 */
public class ConvertDoublyLinkedListToArrayI {

    public int[] toArray(Node head) {
        int count = 0, i = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        int[] res = new int[count];
        while (head != null) {
            res[i++] = head.val;
            head = head.next;
        }
        return res;
    }

    /*
    // Definition for a Node.
     */
    private static class Node {
        public int val;
        public Node prev;
        public Node next;
    };
}
