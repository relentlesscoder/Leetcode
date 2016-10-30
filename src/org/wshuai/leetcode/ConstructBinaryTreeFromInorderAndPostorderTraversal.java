package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/30/2016.
 * #106 https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    if(inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0){
      return null;
    }
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    int len = inorder.length;
    for(int i = 0; i < len; i++){
      map.put(inorder[i], i);
    }
    return buildTreeUtil(inorder, postorder, 0, len-1, 0, len-1, map);
  }

  private TreeNode buildTreeUtil(int[] in, int[] post, int s1, int e1, int s2, int e2, Map<Integer, Integer> pos){
    if(s2 > e2){
      return null;
    }
    int val = post[e2];
    TreeNode root = new TreeNode(val);
    if(s2 == e2){
      return root;
    }
    int idx = pos.get(val);
    int ns2 = s2+idx-s1;
    root.left = buildTreeUtil(in, post, s1, idx-1, s2, ns2-1, pos);
    root.right = buildTreeUtil(in, post, idx+1, e1, ns2, e2-1, pos);

    return root;
  }
}
