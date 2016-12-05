package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 11/21/2016.
 * #285 https://leetcode.com/problems/inorder-successor-in-bst/
 */
public class InorderSuccessorInBST {
  //O(n), iterative
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if(root == null || p == null){
      return null;
    }
    boolean nxt = false;
    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode current = root;
    while (current != null || !stack.empty()){
      if(current != null){
        stack.push(current);
        current = current.left;
      }
      else{
        TreeNode parent = stack.pop();
        if(nxt){
          return parent;
        }
        nxt = parent == p;
        current = parent.right;
      }
    }
    return null;
  }
}
