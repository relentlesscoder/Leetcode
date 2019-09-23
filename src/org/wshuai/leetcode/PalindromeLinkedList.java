package org.wshuai.leetcode;

/**
 * Created by Wei on 9/2/2016.
 */
public class PalindromeLinkedList {
  public static boolean isPalindrome(LinkedListNode head) {
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

    LinkedListNode slow = head;
    LinkedListNode fast = head;
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

    LinkedListNode prev = null;
    LinkedListNode curr = head;
    LinkedListNode next = head.next;
    while(curr != slow){
      curr.next = prev;
      prev = curr;
      curr = next;
      next = next.next;
    }

    LinkedListNode left = prev;
    LinkedListNode right = slow;
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
