package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.SecondMinimumNodeInABinaryTree;
import org.wshuai.leetcode.TreeNode;

public class SecondMinimumNodeInABinaryTreeTest {
  @Test
  public void testcase(){
    SecondMinimumNodeInABinaryTree sm = new SecondMinimumNodeInABinaryTree();
    TreeNode root = new TreeNode(2);
    root.left = new TreeNode(2);
    root.right = new TreeNode(5);
    root.right.left = new TreeNode(5);
    root.right.right = new TreeNode(7);
    int val = sm.findSecondMinimumValue(root);
  }
}
