package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #124 https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class BinaryTreeMaximumPathSum {
  public int maxPathSum(TreeNode root) {
    MaxVal maxVal = new MaxVal();
    maxVal.val = Integer.MIN_VALUE;
    int maxCross = findMaxPathSum(root, maxVal);
    int mv = maxVal.val;
    return maxCross > mv ? maxCross : mv;
  }

  private int findMaxPathSum(TreeNode node, MaxVal maxVal){
    if(node == null){
      return 0;
    }
    int nval = node.val;
    int left = findMaxPathSum(node.left, maxVal);
    int right = findMaxPathSum(node.right, maxVal);

    //For each node if the val path go through it, there are 3 scenarios:
    //1. left + node
    //2. right + node
    //3. cross
    int gc = left > right ? left : right;
    int maxChild = gc > 0 ? gc + nval : nval;
    int crs = left + right + nval;
    int maxTop = crs > maxChild ? crs : maxChild;
    int mv = maxVal.val;
    maxVal.val = mv > maxTop ? mv : maxTop;
    //return val child path for the parent node calculation
    return maxChild;
  }

  class MaxVal{
    public int val;
  }
}
