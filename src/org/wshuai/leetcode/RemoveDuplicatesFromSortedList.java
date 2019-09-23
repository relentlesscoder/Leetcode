package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/16.
 * #83 https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */
public class RemoveDuplicatesFromSortedList {
  public LinkedListNode deleteDuplicates(LinkedListNode head) {
    if(head == null || head.next == null){
      return head;
    }
    LinkedListNode last = head;
    LinkedListNode curr = head;
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
