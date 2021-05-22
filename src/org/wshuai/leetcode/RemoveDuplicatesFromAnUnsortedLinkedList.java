package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 05/22/2021.
 * #1836 https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list/
 */
public class RemoveDuplicatesFromAnUnsortedLinkedList {

    // time O(n), space O(n)
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        Map<Integer, Integer> count = new HashMap<>();
        ListNode root = new ListNode(-1), prev = root, cur = head;
        root.next = head;
        while(cur != null){
            count.put(cur.val, count.getOrDefault(cur.val, 0) + 1);
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            if(count.get(cur.val) > 1){
                prev.next = cur.next;
            }else{
                prev = cur;
            }
            cur = cur.next;
        }
        return root.next;
    }

    // Definition for singly-linked list.
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
