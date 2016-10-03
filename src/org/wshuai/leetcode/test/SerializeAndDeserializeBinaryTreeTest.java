package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.SerializeAndDeserializeBinaryTree;
import org.wshuai.leetcode.TreeNode;

/**
 * Created by Wei on 10/2/2016.
 */
public class SerializeAndDeserializeBinaryTreeTest {
  @Test
  public void testcase(){
    SerializeAndDeserializeBinaryTree sd = new SerializeAndDeserializeBinaryTree();
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    String s = sd.serialize(root);
    TreeNode x = sd.deserialize(s);
  }
}
