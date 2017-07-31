package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ConvertBSTToGreaterTree;
import org.wshuai.leetcode.TreeNode;

/**
 * Created by Wei on 7/20/2017.
 */
public class ConvertBSTToGreaterTreeTest {
  @Test
  public void testcase(){
    ConvertBSTToGreaterTree cb = new ConvertBSTToGreaterTree();
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(2);
    root.right = new TreeNode(13);
    TreeNode newRoot = cb.convertBST(root);
  }
}
