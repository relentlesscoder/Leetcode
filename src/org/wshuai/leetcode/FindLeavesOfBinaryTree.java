package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/1/2016.
 */
public class FindLeavesOfBinaryTree {
  public List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> lst = new ArrayList<List<Integer>>();
    if(root == null){
      return lst;
    }
    findLeavesUtil(root, lst);
    return lst;
  }

  private int findLeavesUtil(TreeNode root, List<List<Integer>> lst){
    if(root == null){
      return -1;
    }
    int left = findLeavesUtil(root.left, lst);
    int right = findLeavesUtil(root.right, lst);
    int depth = (left > right ? left : right) + 1;
    if(depth >= lst.size()){
      List<Integer> ls = new ArrayList<Integer>();
      ls.add(root.val);
      lst.add(ls);
    }else{
      List<Integer> ls = lst.get(depth);
      ls.add(root.val);
    }
    return depth;
  }
}
