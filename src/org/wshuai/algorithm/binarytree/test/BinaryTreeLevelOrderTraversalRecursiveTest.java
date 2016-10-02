package org.wshuai.algorithm.binaryTree.test;

import org.junit.Test;
import org.wshuai.algorithm.binaryTree.BinaryTreeLevelOrderTraversalRecursive;
import org.wshuai.algorithm.binaryTree.BinaryTreeNode;

import java.util.List;

/**
 * Created by Wei on 8/30/16.
 */
public class BinaryTreeLevelOrderTraversalRecursiveTest {
  @Test
  public void testcase(){
    BinaryTreeNode root = new BinaryTreeNode(3);
    root.left = new BinaryTreeNode(5);
    root.right = new BinaryTreeNode(1);
    root.left.left = new BinaryTreeNode(7);
    root.left.right = new BinaryTreeNode(6);
    root.right.left = new BinaryTreeNode(9);
    root.right.right = new BinaryTreeNode(4);
    List<List<Integer>> lst = BinaryTreeLevelOrderTraversalRecursive.traversal(root);
  }
}
