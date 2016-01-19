package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 1/19/2016.
 * #104 https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class MaximumDepthOfBinaryTree {
  public int maxDepthRecursive(TreeNode root) {
    if(root == null){
      return 0;
    }
    return Math.max(maxDepthRecursive(root.left) + 1, maxDepthRecursive(root.right) + 1);
  }

  public int maxDepthIterative(TreeNode root) {
    if(root == null){
      return 0;
    }

    int result = 0;
    Stack<TreeNode> stack = new Stack<TreeNode>();

    TreeNode current = root;

    return result;
  }
}
