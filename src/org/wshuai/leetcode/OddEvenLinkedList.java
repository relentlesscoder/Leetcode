package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #328 https://leetcode.com/problems/odd-even-linked-list/
 */
public class OddEvenLinkedList {
  public ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null || head.next.next == null)
    {
      return head;
    }
    ListNode curr = head;
    ListNode even = head.next;
    ListNode nxt = null;

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
