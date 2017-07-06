package org.wshuai.leetcode;

/**
 * Created by Wei on 7/6/2017.
 * #617 https://leetcode.com/problems/merge-two-binary-trees/
 */
public class MergeTwoBinaryTrees {
  public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    TreeNode root = null;
    if(t1 == null && t2 == null){
      return root;
    }

    if(t1 == null){
      root = new TreeNode(t2.val);
    }else if(t2 == null){
      root = new TreeNode(t1.val);
    }else{
      root = new TreeNode(t1.val + t2.val);
    }

    root.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
    root.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);
    return root;
  }
}
