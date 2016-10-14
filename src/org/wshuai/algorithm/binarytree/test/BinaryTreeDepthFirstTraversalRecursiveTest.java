package org.wshuai.algorithm.binarytree.test;

import org.wshuai.algorithm.binarytree.BinaryTreeNode;
import org.junit.Test;
import org.wshuai.algorithm.binaryTree.BinaryTreeDepthFirstTraversalRecursive;

import java.util.List;

/**
 * Created by Wei on 8/30/16.
 */
public class BinaryTreeDepthFirstTraversalRecursiveTest {
  @Test
  public void testcase(){
    BinaryTreeNode root = new BinaryTreeNode(3);
    root.left = new BinaryTreeNode(5);
    root.right = new BinaryTreeNode(1);
    root.left.left = new BinaryTreeNode(7);
    root.left.right = new BinaryTreeNode(6);
    root.right.left = new BinaryTreeNode(9);
    root.right.right = new BinaryTreeNode(4);
    List<Integer> lst1 = BinaryTreeDepthFirstTraversalRecursive.inOrderTraversal(root);
    List<Integer> lst2 = BinaryTreeDepthFirstTraversalRecursive.preOrderTraversal(root);
    List<Integer> lst3 = BinaryTreeDepthFirstTraversalRecursive.postOrderTraversal(root);
  }
}
