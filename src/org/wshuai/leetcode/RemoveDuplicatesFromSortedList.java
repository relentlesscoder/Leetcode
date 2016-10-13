package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/16.
 * #83 https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */
public class RemoveDuplicatesFromSortedList {
  public ListNode deleteDuplicates(ListNode head) {
    if(head == null || head.next == null){
      return head;
    }
    ListNode last = head;
    ListNode curr = head;
    while(curr != null){
      if(curr.val != last.val){
        last.next = curr;
        last = curr;
      }
      curr = curr.next;
    }
    last.next = null;
    return head;
  }
}
