package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 1/20/16.
 * #144 https://leetcode.com/problems/binary-tree-preorder-traversal/
 */
public class BinaryTreePreorderTraversal {
  public static List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();

    if(root == null){
      return result;
    }

    Stack<TreeNode> stack = new Stack<TreeNode>();
    stack.push(root);
    TreeNode prev = null;
    while (!stack.isEmpty()){
      TreeNode current = stack.peek();
      if(prev == null || prev.left == current || prev.right == current){
        result.add(current.val);
        if(current.left != null){
          stack.push(current.left);
        }
        else if(current.right != null){
          stack.push(current.right);
        }
      }
      else if(current.left == prev){
        if(current.right != null){
          stack.push(current.right);
        }
      }
      else{
        stack.pop();
      }

      prev = current;
    }

    return result;
  }

  public List<Integer> preorderTraversalSimple(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();

    if(root == null){
      return result;
    }

    Stack<TreeNode> stack = new Stack<TreeNode>();
    stack.push(root);
    while (!stack.empty()){
      TreeNode current = stack.pop();
      result.add(current.val);

      if(current.left != null){
        stack.push(current.left);
      }
      if(current.right != null){
        stack.push(current.right);
      }
    }

    return result;
  }
}
