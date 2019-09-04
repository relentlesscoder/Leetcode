package org.wshuai.leetcode;

/**
 * Created by Wei on 9/4/19.
 * #708 https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/
 */
public class InsertIntoACyclicSortedList {
    public LinkedListNode insert(LinkedListNode head, int insertVal) {
        if(head == null){
            LinkedListNode res = new LinkedListNode(insertVal, null);
            res.next = res;
            return res;
        }
        LinkedListNode curr = head.next;
        LinkedListNode start = head;
        LinkedListNode end = head;
        int max = head.val;
        int min = head.val;
        while(curr != head){
            if(curr.val >= max){
                max = curr.val;
                end = curr;
            }
            if(curr.val < min){
                min = curr.val;
                start = curr;
            }
            curr = curr.next;
        }
        if(insertVal >= end.val || insertVal <= start.val){
            LinkedListNode node = new LinkedListNode(insertVal, start);
            end.next = node;
        }else{
            curr = start;
            while(curr != end){
                if(curr.val <= insertVal && curr.next.val >= insertVal){
                    LinkedListNode node = new LinkedListNode(insertVal, curr.next);
                    curr.next = node;
                    break;
                }
                curr = curr.next;
            }
        }
        return head;
    }
}
