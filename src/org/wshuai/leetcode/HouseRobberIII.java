package org.wshuai.leetcode;

/**
 * Created by Wei on 10/30/16.
 */
public class HouseRobberIII {

  //2ms, 2D dynamic programming
  public int rob(TreeNode root) {
    int[] maxs = robUtil(root);
    return Math.max(maxs[0], maxs[1]);
  }

  private int[] robUtil(TreeNode root){
    int[] arr = new int[2];
    if(root == null){
      arr[0] = 0;
      arr[1] = 0;
      return arr;
    }

    int[] left = robUtil(root.left);
    int[] right = robUtil(root.right);
    arr[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    arr[1] = left[0] + right[0] + root.val;
    return arr;
  }

  //697ms
  public int robRecursive(TreeNode root) {
    if(root == null){
      return 0;
    }
    if(root.left == null && root.right == null){
      return root.val;
    }
    int l1 = 0;
    int l2 = 0;
    if(root.left != null){
      l1 = robRecursive(root.left);
      l2 = robRecursive(root.left.left) + robRecursive(root.left.right);
    }
    int r1 = 0;
    int r2 = 0;
    if(root.right != null){
      r1 = robRecursive(root.right);
      r2 = robRecursive(root.right.left) + robRecursive(root.right.right);
    }
    return Math.max(root.val + l2 + r2, l1 + r1);
  }
}
