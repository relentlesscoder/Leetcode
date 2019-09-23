package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #328 https://leetcode.com/problems/odd-even-linked-list/
 */
public class OddEvenLinkedList {
  public LinkedListNode oddEvenList(LinkedListNode head) {
    if (head == null || head.next == null || head.next.next == null)
    {
      return head;
    }
    LinkedListNode curr = head;
    LinkedListNode even = head.next;
    LinkedListNode nxt = null;

    boolean odd = true;
    while (curr != null)
    {
      nxt = curr.next;
      if (nxt != null)
      {
        curr.next = nxt.next;
        if (nxt.next == null && odd)
        {
          curr.next = even;
        }
      }
      else
      {
        curr.next = null;
        if (odd)
        {
          curr.next = even;
        }
      }
      curr = nxt;
      odd = !odd;
    }

    return head;
  }
}
