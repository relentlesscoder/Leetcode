package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 7/20/2017.
 * #538 https://leetcode.com/problems/convert-bst-to-greater-tree/
 */
public class ConvertBSTToGreaterTree {
  //O(n), Right -> Root -> Left Tree Traversal
  public TreeNode convertBST(TreeNode root) {
    if(root == null){
      return root;
    }
    int total = 0;
    Stack<TreeNode> stk = new Stack<TreeNode>();
    TreeNode curr = root;
    while(curr != null || !stk.isEmpty()){
      if(curr != null){
        stk.push(curr);
        curr = curr.right;
      }else{
        curr = stk.pop();
        curr.val += total;
        total = curr.val;
        curr = curr.left;
      }
    }
    return root;
  }
}
