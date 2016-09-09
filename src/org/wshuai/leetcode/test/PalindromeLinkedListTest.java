package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ListNode;
import org.wshuai.leetcode.PalindromeLinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 9/2/2016.
 */
public class PalindromeLinkedListTest {
  @Test
  public void testcase(){
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(2);
    head.next.next.next = new ListNode(2);
    head.next.next.next.next = new ListNode(1);
    boolean isPal = PalindromeLinkedList.isPalindrome(head);
    assertEquals(isPal, true);
  }

  @Test
  public void testcase1(){
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(2);
    head.next.next.next = new ListNode(1);
    boolean isPal = PalindromeLinkedList.isPalindrome(head);
    assertEquals(isPal, true);
  }

  @Test
  public void testcase2(){
    ListNode head = new ListNode(1);
    head.next = new ListNode(1);
    head.next.next = new ListNode(2);
    head.next.next.next = new ListNode(1);
    boolean isPal = PalindromeLinkedList.isPalindrome(head);
    assertEquals(isPal, false);
  }
}
