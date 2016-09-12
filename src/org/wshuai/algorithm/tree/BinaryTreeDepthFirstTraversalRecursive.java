package org.wshuai.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/30/16.
 */
public class BinaryTreeDepthFirstTraversalRecursive {
  public static List<Integer> inOrderTraversal(BinaryTreeNode root){
    List<Integer> lst = new ArrayList<Integer>();
    inOrderTraversalHelper(root, lst);
    return lst;
  }

  private static void inOrderTraversalHelper(BinaryTreeNode root, List<Integer> lst){
    if(root == null){
      return;
    }
    inOrderTraversalHelper(root.left, lst);
    lst.add(root.value);
    inOrderTraversalHelper(root.right, lst);
  }

  public static List<Integer> preOrderTraversal(BinaryTreeNode root){
    List<Integer> lst = new ArrayList<Integer>();
    preOrderTraversalHelper(root, lst);
    return lst;
  }

  private static void preOrderTraversalHelper(BinaryTreeNode root, List<Integer> lst){
    if(root == null){
      return;
    }
    lst.add(root.value);
    preOrderTraversalHelper(root.left, lst);
    preOrderTraversalHelper(root.right, lst);
  }

  public static List<Integer> postOrderTraversal(BinaryTreeNode root){
    List<Integer> lst = new ArrayList<Integer>();
    postOrderTraversalHelper(root, lst);
    return lst;
  }

  private static void postOrderTraversalHelper(BinaryTreeNode root, List<Integer> lst){
    if(root == null){
      return;
    }
    postOrderTraversalHelper(root.left, lst);
    postOrderTraversalHelper(root.right, lst);
    lst.add(root.value);
  }
}
