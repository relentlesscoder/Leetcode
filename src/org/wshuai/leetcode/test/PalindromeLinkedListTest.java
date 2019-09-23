package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LinkedListNode;
import org.wshuai.leetcode.PalindromeLinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 9/2/2016.
 */
public class PalindromeLinkedListTest {
  @Test
  public void testcase(){
    LinkedListNode head = new LinkedListNode(1);
    head.next = new LinkedListNode(2);
    head.next.next = new LinkedListNode(2);
    head.next.next.next = new LinkedListNode(2);
    head.next.next.next.next = new LinkedListNode(1);
    boolean isPal = PalindromeLinkedList.isPalindrome(head);
    assertEquals(isPal, true);
  }

  @Test
  public void testcase1(){
    LinkedListNode head = new LinkedListNode(1);
    head.next = new LinkedListNode(2);
    head.next.next = new LinkedListNode(2);
    head.next.next.next = new LinkedListNode(1);
    boolean isPal = PalindromeLinkedList.isPalindrome(head);
    assertEquals(isPal, true);
  }

  @Test
  public void testcase2(){
    LinkedListNode head = new LinkedListNode(1);
    head.next = new LinkedListNode(1);
    head.next.next = new LinkedListNode(2);
    head.next.next.next = new LinkedListNode(1);
    boolean isPal = PalindromeLinkedList.isPalindrome(head);
    assertEquals(isPal, false);
  }
}
