package org.wshuai.leetcode;

/**
 * Created by Wei on 9/2/2016.
 */
public class PalindromeLinkedList {
  public static boolean isPalindrome(ListNode head) {
    // base cases
    if(head == null){
      return true;
    }
    if(head.next == null){
      return true;
    }
    if(head.next.next == null){
      return head.val == head.next.val;
    }
    if(head.next.next.next == null){
      return head.val == head.next.next.val;
    }

    ListNode slow = head;
    ListNode fast = head;
    boolean odd = false;

    while(fast != null){
      slow = slow.next;
      fast = fast.next;
      if(fast != null){
        fast = fast.next;
        if(fast != null && fast.next == null){
          odd = true;
          break;
        }
      }
    }

    ListNode prev = null;
    ListNode curr = head;
    ListNode next = head.next;
    while(curr != slow){
      curr.next = prev;
      prev = curr;
      curr = next;
      next = next.next;
    }

    ListNode left = prev;
    ListNode right = slow;
    if(odd){
      right = right.next;
    }

    while(left != null && right != null){
      if(left.val != right.val){
        return false;
      }
      left = left.next;
      right = right.next;
    }

    return true;
  }
}
