package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 11/10/2016.
 * #199 https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView {
  //O(n), binary tree level order traversal
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> lst = new ArrayList<Integer>();
    if(root == null){
      return lst;
    }
    LinkedList<TreeNode> curr = new LinkedList<TreeNode>();
    LinkedList<TreeNode> next = new LinkedList<TreeNode>();
    curr.offer(root);
    while(!curr.isEmpty()){
      TreeNode node = curr.poll();
      if(node.left != null){
        next.offer(node.left);
      }
      if(node.right != null){
        next.offer(node.right);
      }
      if(curr.isEmpty()){
        lst.add(node.val);
        curr = next;
        next = new LinkedList<TreeNode>();
      }
    }
    return lst;
  }
}
