package org.wshuai.leetcode;

/**
 * Created by Wei on 11/2/16.
 * #92 https://leetcode.com/problems/reverse-linked-list-ii/
 */
public class ReverseLinkedListII {
  public ListNode reverseBetween(ListNode head, int m, int n) {
    if(m >= n){
      return head;
    }

    ListNode prev = null;
    ListNode curr = head;
    ListNode front = null;
    ListNode tail = null;
    int i = 1;
    while(i <= n){
      if(i < m){
        prev = curr;
        curr = curr.next;
      }
      if(i == m){
        front = prev;
        tail = curr;
        prev = null;
      }
      if(i >= m){
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }
      i++;
    }
    tail.next = curr;
    if(front != null){
      front.next = prev;
      return head;
    }else{
      return prev;
    }
  }
}
