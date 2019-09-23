package org.wshuai.leetcode;

/**
 * Created by Wei on 9/24/2016.
 */
public class PlusOneLinkedList {
  public LinkedListNode plusOne(LinkedListNode head) {
    LinkedListNode root = reverseLinkedList(head);
    int carry = 0;
    LinkedListNode curr = root;
    LinkedListNode prev = null;
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
      LinkedListNode n = new LinkedListNode(carry);
      prev.next = n;
    }
    return reverseLinkedList(root);
  }

  private LinkedListNode reverseLinkedList(LinkedListNode head){
    LinkedListNode curr = head;
    LinkedListNode prev = null;
    while(curr != null){
      LinkedListNode nxt = curr.next;
      curr.next = prev;
      prev = curr;
      curr = nxt;
    }
    return prev;
  }
}
