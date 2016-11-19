package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/15/16.
 * #314 https://leetcode.com/problems/binary-tree-vertical-order-traversal/
 */
public class BinaryTreeVerticalOrderTraversal {
  //O(n), level order traversal
  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> lst = new ArrayList<List<Integer>>();
    if(root == null){
      return lst;
    }
    List<TreeNode> nodes = new ArrayList<TreeNode>();
    List<Integer> dis = new ArrayList<Integer>();
    nodes.add(root);
    dis.add(0);
    int i = 0;
    int min = 0;
    Set<Integer> set = new HashSet<Integer>();
    set.add(0);
    while(i < nodes.size()){
      TreeNode node = nodes.get(i);
      int nd = dis.get(i);
      if(node.left != null){
        nodes.add(node.left);
        int d = nd-1;
        min = d < min ? d : min;
        dis.add(d);
        set.add(d);
      }
      if(node.right != null){
        nodes.add(node.right);
        int d = nd+1;
        dis.add(d);
        set.add(d);
      }
      i++;
    }
    int diff = 0-min;
    int size = nodes.size();
    for(int j = 0; j < set.size(); j++){
      lst.add(null);
    }
    for(int j = 0; j < size; j++){
      int idx = dis.get(j)+diff;
      int val = nodes.get(j).val;
      List<Integer> ls = lst.get(idx);
      if(ls == null){
        ls = new ArrayList<Integer>();
        ls.add(val);
        lst.set(idx, ls);
      }else{
        ls.add(val);
      }
    }
    return lst;
  }
}
