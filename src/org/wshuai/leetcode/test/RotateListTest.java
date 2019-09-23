package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LinkedListNode;
import org.wshuai.leetcode.RotateList;

/**
 * Created by Wei on 10/9/16.
 */
public class RotateListTest {
  @Test
  public void testcase(){
    LinkedListNode head = new LinkedListNode(1);
    RotateList rl = new RotateList();
    LinkedListNode r = rl.rotateRight(head, 1);
  }
}
