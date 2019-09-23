package org.wshuai.leetcode;

/**
 * Created by Wei on 10/9/16.
 * #61 https://leetcode.com/problems/rotate-list/
 */
public class RotateList {
  public LinkedListNode rotateRight(LinkedListNode head, int k) {
    if(head == null || k <= 0){
      return head;
    }
    int count = 0;
    LinkedListNode curr = head;
    LinkedListNode prev = null;
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
