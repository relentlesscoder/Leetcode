package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 11/19/16.
 * #255 https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/
 */
public class VerifyPreorderSequenceInBinarySearchTree {
  //O(n)
  public boolean verifyPreorder(int[] preorder) {
    if(preorder == null || preorder.length == 0){
      return true;
    }
    int len = preorder.length;
    int min = Integer.MIN_VALUE;
    Stack<Integer> stk = new Stack<Integer>();
    for(int i = 0; i < len; i++){
      //All nodes of the right subtree are greater than the root
      if(preorder[i] < min){
        return false;
      }
      //Keep polling the stack until finding the parent of the current node
      while(!stk.isEmpty() && preorder[i] > stk.peek()){
        min = stk.pop();
      }
      stk.push(preorder[i]);
    }
    return true;
  }
}
