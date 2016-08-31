package org.wshuai.leetcode;

import static org.wshuai.leetcode.MergeTwoSortedList.mergeTwoLists;

/**
 * Created by Wei on 8/20/16.
 */
public class SortList {
    public static ListNode sortList(ListNode head) {
        if(head != null && head.next != null){
            if(head.next.next == null){
                if(head.val > head.next.val){
                    int temp = head.val;
                    head.val = head.next.val;
                    head.next.val = temp;
                }
                return head;
            }else{
                ListNode curr = head;
                ListNode mid = head;
                while(curr != null && curr.next != null){
                  curr = curr.next.next;
                  mid = mid.next;
                }
                ListNode nxt = mid.next;
                mid.next = null;
                ListNode l = sortList(head);
                ListNode r = sortList(nxt);
                return mergeTwoLists(l, r);
            }
        }else{
            return head;
        }
    }
}
