package org.wshuai.leetcode;

/**
 * Created by Wei on 01/10/2026.
 * #3294 https://leetcode.com/problems/convert-doubly-linked-list-to-array-ii/
 */
public class ConvertDoublyLinkedListToArrayII {

    // time O(n), space O(n)
    public int[] toArray(Node node) {
        Node curr = node, head = null;
        // 先找到头节点
        while (curr != null) {
            head = curr;
            curr = curr.prev;
        }
        // 接下来同 #3263
        int cnt = 0, idx = 0;
        curr = head;
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
