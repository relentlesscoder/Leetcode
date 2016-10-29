package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.BinaryTreeLongestConsecutiveSequence;
import org.wshuai.leetcode.TreeNode;

/**
 * Created by Wei on 10/28/2016.
 */
public class BinaryTreeLongestConsecutiveSequenceTest {
  @Test
  public void testcase(){
    BinaryTreeLongestConsecutiveSequence bt = new BinaryTreeLongestConsecutiveSequence();
    TreeNode root = new TreeNode(1);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(2);
    root.right.right = new TreeNode(4);
    root.right.right.left = new TreeNode(5);
    int x = bt.longestConsecutiveRecursive(root);
  }

  @Test
  public void testcase1(){
    BinaryTreeLongestConsecutiveSequence bt = new BinaryTreeLongestConsecutiveSequence();
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(2);
    root.right = new TreeNode(4);
    root.left.left = new TreeNode(3);
    int x = bt.longestConsecutiveIterative(root);
  }
}
