package org.wshuai.leetcode;

/**
 * Created by Wei on 1/23/16.
 * #235 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestorOfBST {
  public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if(root == null || p == null || q == null){
      return null;
    }

    if(p == root || q == root){
      return root;
    }

    if(p.val > root.val && q.val > root.val){
      return lowestCommonAncestor(root.right, p, q);
    }
    else if(p.val < root.val && q.val < root.val){
      return lowestCommonAncestor(root.left, p, q);
    }
    else{
      return root;
    }
  }
}
