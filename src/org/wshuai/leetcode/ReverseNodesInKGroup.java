package org.wshuai.leetcode;

/**
 * Created by Wei on 11/8/16.
 * #25 https://leetcode.com/problems/reverse-nodes-in-k-group/
 */
public class ReverseNodesInKGroup {
  //O(n)
  public LinkedListNode reverseKGroup(LinkedListNode head, int k)
  {
    if (head == null || k <= 0)
    {
      return head;
    }
    LinkedListNode root = new LinkedListNode(-1);
    root.next = head;

    LinkedListNode prev = root;
    LinkedListNode curr = head;
    int cnt = 0;
    while (curr != null)
    {
      cnt++;
      if (cnt == k)
      {
        prev = reverseList(prev, curr);
        curr = prev;
        cnt = 0;
      }
      curr = curr.next;
    }

    return root.next;
  }

  private LinkedListNode reverseList(LinkedListNode prev, LinkedListNode tail)
  {
    LinkedListNode head = prev.next;
    LinkedListNode res = head;
    prev.next = tail;
    LinkedListNode nxt = tail.next;
    while (nxt != tail)
    {
      LinkedListNode p = head.next;
      head.next = nxt;
      nxt = head;
      head = p;
    }
    return res;
  }
}
