package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #206 https://leetcode.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {
  public ListNode reverseList(ListNode head) {
    if(head == null){
      return null;
    }

    ListNode prev = null;
    ListNode curr = head;
    while(curr != null){
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    return prev;
  }
}
