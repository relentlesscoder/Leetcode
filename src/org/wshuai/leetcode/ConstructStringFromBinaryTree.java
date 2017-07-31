package org.wshuai.leetcode;

/**
 * Created by Wei on 7/20/2017.
 * #606 https://leetcode.com/problems/construct-string-from-binary-tree/
 */
public class ConstructStringFromBinaryTree {
  public String tree2str(TreeNode t) {
    if(t == null){
      return "";
    }
    String res = t.val + "";
    String left = tree2str(t.left);
    String right = tree2str(t.right);

    if(t.left == null && t.right == null){
      return res;
    }
    if(t.left == null){
      return  res + "()(" + right + ")";
    }
    if(t.right == null){
      return res + "(" + left + ")";
    }
    return res + "(" + left + ")(" + right + ")";
  }
}
