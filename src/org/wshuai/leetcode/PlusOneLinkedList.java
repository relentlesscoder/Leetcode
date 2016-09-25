package org.wshuai.leetcode;

/**
 * Created by Wei on 9/24/2016.
 */
public class PlusOneLinkedList {
  public ListNode plusOne(ListNode head) {
    ListNode root = reverseLinkedList(head);
    int carry = 0;
    ListNode curr = root;
    ListNode prev = null;
    while(curr != null){
      int val = curr.val;
      if(curr == root){
        val++;
      }
      val += carry;
      carry = val >= 10 ? 1 : 0;
      curr.val = val >= 10 ? val - 10 : val;
      prev = curr;
      curr = curr.next;
    }
    if(carry > 0){
      ListNode n = new ListNode(carry);
      prev.next = n;
    }
    return reverseLinkedList(root);
  }

  private ListNode reverseLinkedList(ListNode head){
    ListNode curr = head;
    ListNode prev = null;
    while(curr != null){
      ListNode nxt = curr.next;
      curr.next = prev;
      prev = curr;
      curr = nxt;
    }
    return prev;
  }
}
