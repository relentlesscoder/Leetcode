package org.wshuai.leetcode;

/**
 * Created by Wei on 4/3/2017.
 * #99 https://leetcode.com/problems/recover-binary-search-tree/
 */
public class RecoverBinarySearchTree {
  public void recoverTree(TreeNode root) {
    TreeNode curr = root;
    TreeNode temp = null;
    TreeNode prev = null;
    TreeNode first = null;
    TreeNode second = null;

    while(curr != null){
      if(curr.left == null){
        if(prev!=null && prev.val > curr.val){
          if(first==null)
          {
            first = prev;
            second = curr;
          }
          else{
            second = curr;
          }
        }
        prev = curr;
        curr = curr.right;
      }else{
        temp = curr.left;
        while(temp.right != null && temp.right != curr){
          temp = temp.right;
        }

        if(temp.right == null){
          temp.right = curr;
          curr = curr.left;
        }else{
          if(prev!=null && prev.val > curr.val){
            if(first==null)
            {
              first = prev;
              second = curr;
            }
            else{
              second = curr;
            }
          }
          prev = curr;
          temp.right = null;
          curr = curr.right;
        }
      }
    }
    //swap two nodes
    if(first != null && second != null){
      int v = first.val;
      first.val = second.val;
      second.val = v;
    }
  }
}
