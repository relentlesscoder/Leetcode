package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ConvertSortedListToBinarySearchTree;
import org.wshuai.leetcode.ListNode;
import org.wshuai.leetcode.TreeNode;

/**
 * Created by Wei on 10/30/2016.
 */
public class ConvertSortedListToBinarySearchTreeTest {
  @Test
  public void testcase(){
    ConvertSortedListToBinarySearchTree cs = new ConvertSortedListToBinarySearchTree();
    ListNode root = new ListNode(3);
    root.next = new ListNode(5);
    root.next.next = new ListNode(8);
    TreeNode tn = cs.sortedListToBST(root);
  }
}
