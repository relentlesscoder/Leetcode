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

    SumObj res = new SumObj();
    sumNumbersUtil(root, 0, res);
    return res.sum;
  }

  private void sumNumbersUtil(TreeNode root, int csum, SumObj res){
    if(root == null){
      return;
    }
    int nsum = csum*10 + root.val;
    if(root.left == null && root.right == null){
      res.sum += nsum;
      return;
    }
    sumNumbersUtil(root.left, nsum, res);
    sumNumbersUtil(root.right, nsum, res);
  }
}

class SumObj{
  int sum = 0;
}
