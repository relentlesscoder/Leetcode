package org.wshuai.leetcode;

/**
 * Created by Wei on 8/9/19.
 * #876 https://leetcode.com/problems/middle-of-the-linked-list/
 */
public class MiddleOfTheLinkedList {
    public LinkedListNode middleNode(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
