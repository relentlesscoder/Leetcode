package org.wshuai.leetcode;

/**
 * Created by Wei on 10/9/16.
 */
public class RotateList {
  public ListNode rotateRight(ListNode head, int k) {
    if(head == null || k <= 0){
      return head;
    }
    int count = 0;
    ListNode curr = head;
    ListNode prev = null;
    while(curr != null){
      prev = curr;
      curr = curr.next;
      count++;
    }

    k = k%count;
    if(k > 0){
      prev.next = head;
      curr = head;
      prev = null;
      while(count > k){
        prev = curr;
        curr = curr.next;
        count--;
      }

      prev.next = null;
      return curr;
    }

    return head;
  }
}