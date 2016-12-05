package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/19/16.
 * #95 https://leetcode.com/problems/unique-binary-search-trees-ii/
 */
public class UniqueBinarySearchTreesII {
  //O(n^2), Divide & Conquer
  //Same idea as https://leetcode.com/problems/different-ways-to-add-parentheses/
  public List<TreeNode> generateTrees(int n) {
    List<TreeNode> lst = new ArrayList<TreeNode>();
    if(n <= 0){
      return lst;
    }
    lst = generateTreesUtil(1, n);
    return lst;
  }

  private List<TreeNode> generateTreesUtil(int start, int end){
    List<TreeNode> nodes = new ArrayList<TreeNode>();
    if(start > end){
      nodes.add(null);
      return nodes;
    }
    for(int i = start; i <= end; i++){
      List<TreeNode> lNodes = generateTreesUtil(start, i-1);
      List<TreeNode> rNodes = generateTreesUtil(i+1, end);
      int lsize = lNodes.size();
      int rsize = rNodes.size();
      for(int j = 0; j < lsize; j++){
        for(int k = 0; k < rsize; k++){
          TreeNode root = new TreeNode(i);
          root.left = lNodes.get(j);
          root.right = rNodes.get(k);
          nodes.add(root);
        }
      }
    }
    return nodes;
  }
}
