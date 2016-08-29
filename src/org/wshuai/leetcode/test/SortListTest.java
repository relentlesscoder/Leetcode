package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ListNode;
import org.wshuai.leetcode.SortList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/20/16.
 */
public class SortListTest {
  @Test
  public void testcase(){
    ListNode node1 = new ListNode(3);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(4);
    node1.next = node2;
    node2.next = node3;
    ListNode node = SortList.sortList(node1);
  }
}
