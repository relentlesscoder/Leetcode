package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ListNode;
import org.wshuai.leetcode.RotateList;

/**
 * Created by Wei on 10/9/16.
 */
public class RotateListTest {
  @Test
  public void testcase(){
    ListNode head = new ListNode(1);
    RotateList rl = new RotateList();
    ListNode r = rl.rotateRight(head, 1);
  }
}
