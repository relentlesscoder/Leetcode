package org.wshuai.leetcode;

/**
 * Created by Wei on 10/3/16.
 */
public class SumOfLeftLeaves {
  public int sumOfLeftLeavesRecursive(TreeNode root) {
    if(root == null){
      return 0;
    }
    int sum = 0;
    sum += sumOfLeftLeavesUtil(root.left, true)
      + sumOfLeftLeavesUtil(root.right, false);

    return sum;
  }

  private int sumOfLeftLeavesUtil(TreeNode node, boolean left){
    if(node == null){
      return 0;
    }
    if(node.left == null && node.right == null){
      if(left){
        return node.val;
      }else{
        return 0;
      }
    }
    return sumOfLeftLeavesUtil(node.left, true)
      + sumOfLeftLeavesUtil(node.right, false);
  }
}
