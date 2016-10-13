package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ListNode;
import org.wshuai.leetcode.RemoveDuplicatesFromSortedListII;

/**
 * Created by Wei on 10/10/16.
 */
public class RemoveDuplicatesFromSortedListIITest {
  @Test
  public void testcase1(){
    RemoveDuplicatesFromSortedListII rd = new RemoveDuplicatesFromSortedListII();
    ListNode root = new ListNode(1);
    root.next = new ListNode(2);
    root.next.next = new ListNode(2);
    ListNode ln = rd.deleteDuplicates(root);
  }

  @Test
  public void testcase2(){
    RemoveDuplicatesFromSortedListII rd = new RemoveDuplicatesFromSortedListII();
    ListNode root = new ListNode(1);
    root.next = new ListNode(2);
    root.next.next = new ListNode(2);
    root.next.next.next = new ListNode(3);
    root.next.next.next.next = new ListNode(4);
    ListNode ln = rd.deleteDuplicates(root);
  }
}
