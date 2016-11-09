package org.wshuai.leetcode;

/**
 * Created by Wei on 11/8/16.
 * #25 https://leetcode.com/problems/reverse-nodes-in-k-group/
 */
public class ReverseNodesInKGroup {
  //O(n)
  public ListNode reverseKGroup(ListNode head, int k)
  {
    if (head == null || k <= 0)
    {
      return head;
    }
    ListNode root = new ListNode(-1);
    root.next = head;

    ListNode prev = root;
    ListNode curr = head;
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

  private ListNode reverseList(ListNode prev, ListNode tail)
  {
    ListNode head = prev.next;
    ListNode res = head;
    prev.next = tail;
    ListNode nxt = tail.next;
    while (nxt != tail)
    {
      ListNode p = head.next;
      head.next = nxt;
      nxt = head;
      head = p;
    }
    return res;
  }
}
