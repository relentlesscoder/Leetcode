package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ConvertSortedListToBinarySearchTree;
import org.wshuai.leetcode.LinkedListNode;
import org.wshuai.leetcode.TreeNode;

/**
 * Created by Wei on 10/30/2016.
 */
public class ConvertSortedListToBinarySearchTreeTest {
  @Test
  public void testcase(){
    ConvertSortedListToBinarySearchTree cs = new ConvertSortedListToBinarySearchTree();
    LinkedListNode root = new LinkedListNode(3);
    root.next = new LinkedListNode(5);
    root.next.next = new LinkedListNode(8);
    TreeNode tn = cs.sortedListToBST(root);
  }
}
