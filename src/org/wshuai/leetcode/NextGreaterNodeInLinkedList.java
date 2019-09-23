package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 9/19/19.
 * #1019 https://leetcode.com/problems/next-greater-node-in-linked-list/
 */
public class NextGreaterNodeInLinkedList {
    public int[] nextLargerNodes(LinkedListNode head) {
        LinkedListNode curr = head;
        int count = 0;
        // first pass to get the size
        while(curr != null){
            count++;
            curr = curr.next;
        }
        int[] res = new int[count];
        Stack<int[]> stack = new Stack<>();
        count = 0;
        curr = head;
        while(curr != null){
            while(!stack.isEmpty() && stack.peek()[0] < curr.val){
                res[stack.pop()[1]] = curr.val;
            }
            stack.push(new int[]{curr.val, count});
            count++;
            curr = curr.next;
        }
        return res;
    }
}
