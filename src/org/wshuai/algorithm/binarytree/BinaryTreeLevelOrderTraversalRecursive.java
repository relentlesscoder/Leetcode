package org.wshuai.algorithm.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/30/16.
 */
public class BinaryTreeLevelOrderTraversalRecursive {
  public static List<List<Integer>> traversal(BinaryTreeNode root){
    List<List<Integer>> lst = new ArrayList<List<Integer>>();
    int h = height(root);
    for(int i = 1; i <= h; i++){
      List<Integer> nodes = new ArrayList<Integer>();
      printLevelNodes(root, i, nodes);
      lst.add(nodes);
    }
    return lst;
  }

  private static int height(BinaryTreeNode root){
    if(root == null){
      return 0;
    }else{
      int left = height(root.left);
      int right = height(root.right);
      int max = left > right ? left : right;
      return max + 1;
    }
  }

  private static void printLevelNodes(BinaryTreeNode root,
                                      int level, List<Integer> lst){
    if(root == null){
      return;
    }
    if(level == 1){
      lst.add(root.value);
    }else{
      printLevelNodes(root.left, level - 1, lst);
      printLevelNodes(root.right, level - 1, lst);
    }
  }
}
