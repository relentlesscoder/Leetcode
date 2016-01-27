package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 1/26/16.
 * #102 https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class BinaryTreeLevelOrderTraversal {
  public static List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> results = new ArrayList<List<Integer>>();

    if(root == null){
      return results;
    }

    List<TreeNode> row = new ArrayList<TreeNode>();
    row.add(root);
    while (row.size() > 0){
      List<Integer> currentRow = new ArrayList<Integer>();
      List<TreeNode> nodes = new ArrayList<TreeNode>();
      for(int i=0; i < row.size(); i++){
        TreeNode current = row.get(i);
        currentRow.add(row.get(i).val);
        if(current.left != null){
          nodes.add(current.left);
        }
        if(current.right != null){
          nodes.add(current.right);
        }
      }
      row = nodes;
      results.add(currentRow);
    }

    return results;
  }
}
