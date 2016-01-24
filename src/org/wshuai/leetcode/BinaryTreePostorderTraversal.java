package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 1/20/16.
 */
public class BinaryTreePostorderTraversal {
  public List<Integer> postorderTraversal(TreeNode root) {
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
        if(current.left != null){
          stack.push(current.left);
        }
        else if(current.right != null){
          stack.push(current.right);
        }
        else{
          result.add(current.val);
        }
      }
      else if(current.left == prev){
        if(current.right != null){
          stack.push(current.right);
        }
        else{
          result.add(current.val);
        }
      }
      else if(current.right == prev){
        result.add(current.val);
      }
      else{
        stack.pop();
      }

      prev = current;
    }
    return result;
  }
}
