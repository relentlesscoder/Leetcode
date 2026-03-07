package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 06/22/2025.
 * #3263 https://leetcode.com/problems/convert-doubly-linked-list-to-array-i/
 */
public class ConvertDoublyLinkedListToArrayI {

    // time O(n), space O(n)
    public int[] toArray(Node head) {
        int cnt = 0, idx = 0;
        Node curr = head;
        while (curr != null) {
            cnt++;
            curr = curr.next;
        }
        int[] res = new int[cnt];
        curr = head;
        while (curr != null) {
            res[idx++] = curr.val;
            curr = curr.next;
        }
        return res;
    }

    /**
     * Definition for a Node.
     **/
    private static class Node {
        public int val;
        public Node prev;
        public Node next;
    }
}
