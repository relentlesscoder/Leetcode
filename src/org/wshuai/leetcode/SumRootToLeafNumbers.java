package org.wshuai.leetcode;

/**
 * Created by Wei on 10/29/16.
 * #129 https://leetcode.com/problems/sum-root-to-leaf-numbers/
 */
public class SumRootToLeafNumbers {
  public int sumNumbers(TreeNode root) {
    if(root == null){
      return 0;
    }

    RefType res = new RefType();
    sumNumbersUtil(root, 0, res);
    return res.val;
  }

  private void sumNumbersUtil(TreeNode root, int csum, RefType res){
    if(root == null){
      return;
    }
    int nsum = csum*10 + root.val;
    if(root.left == null && root.right == null){
      res.val += nsum;
      return;
    }
    sumNumbersUtil(root.left, nsum, res);
    sumNumbersUtil(root.right, nsum, res);
  }
}
