package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ListNode;
import org.wshuai.leetcode.MergeTwoSortedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/16/2016.
 */
public class MergeTwoSortedListTest {
  @Test
  public void validInputsShouldBeMerged(){
    ListNode l1 = new ListNode(2);
    ListNode l2 = new ListNode(1);
    MergeTwoSortedList mt = new MergeTwoSortedList();
    ListNode r = mt.mergeTwoLists(l1, l2);
  }
}
