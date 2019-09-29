package org.wshuai.leetcode;

/**
 * Created by Wei on 9/28/2019.
 * #951 https://leetcode.com/problems/flip-equivalent-binary-trees/
 */
public class FlipEquivalentBinaryTrees {
  public boolean flipEquiv(TreeNode root1, TreeNode root2) {
    if(root1 == null && root2 == null){
      return true;
    }
    if(root1 == null || root2 == null){
      return false;
    }
    if(root1.val == root2.val
      && ((flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right))
      || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left)))){
      return true;
    }
    return false;
  }
}
