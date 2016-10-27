package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #143 https://leetcode.com/problems/reorder-list/
 */
public class ReorderList {
  /**
   * Definition for singly-linked list.
   * public class ListNode {
   *     int val;
   *     ListNode next;
   *     ListNode(int x) { val = x; }
   * }
   */
  public class Solution {
    public void reorderList(ListNode head) {
      if (head == null || head.next == null || head.next.next == null)
      {
        return;
      }

      // Find the middle
      ListNode fast = head;
      ListNode slow = head;
      while (fast != null)
      {
        slow = slow.next;
        fast = fast.next;
        if (fast != null)
        {
          fast = fast.next;
        }
      }

      // Split the list
      ListNode it = head;
      while (it.next != slow)
      {
        it = it.next;
      }
      it.next = null;

      // Reverse the 2nd half
      ListNode prev = null;
      ListNode curr = slow;
      while (curr != null)
      {
        ListNode nxt = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nxt;
      }

      // Reorder
      ListNode left = head;
      ListNode right = prev;
      while (right != null)
      {
        ListNode nxtLeft = left.next;
        ListNode nxtRight = right.next;
        left.next = right;
        right.next = nxtLeft;
        left = nxtLeft;
        right = nxtRight;
      }
    }
  }
}
