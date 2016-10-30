package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/30/2016.
 * #105 https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0){
      return null;
    }
    Map<Integer, Integer> positions = new HashMap<Integer, Integer>();
    int len = inorder.length;
    for(int i = 0; i < len; i++){
      positions.put(inorder[i], i);
    }
    return buildTreeUtil(preorder, inorder, 0, len-1, 0, len-1, positions);
  }

  private TreeNode buildTreeUtil(int[] pre, int[] in, int s1, int e1, int s2, int e2, Map<Integer, Integer> pos){
    if(s1 > e1){
      return null;
    }
    int val = pre[s1];
    TreeNode root = new TreeNode(val);
    if(s1 == e1){
      return root;
    }
    int idx = pos.get(val);
    int ne1 = s1+idx-s2;
    root.left = buildTreeUtil(pre, in, s1+1, ne1, s2, idx-1, pos);
    root.right = buildTreeUtil(pre, in, ne1+1, e1, idx+1, e2, pos);
    return root;
  }
}
