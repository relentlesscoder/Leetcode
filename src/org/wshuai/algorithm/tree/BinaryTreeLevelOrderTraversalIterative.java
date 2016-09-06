package org.wshuai.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Wei on 8/30/16.
 */
public class BinaryTreeLevelOrderTraversalIterative {
  public static List<List<Integer>> traversal(BinaryTreeNode root){
    List<List<Integer>> lst = new ArrayList<List<Integer>>();
    if(root == null){
      return lst;
    }
    Queue<BinaryTreeNode> last = new LinkedList<BinaryTreeNode>();
    Queue<BinaryTreeNode> current = new LinkedList<BinaryTreeNode>();
    List<Integer> nodes = new ArrayList<Integer>();
    last.add(root);
    while (!last.isEmpty()){
      BinaryTreeNode node = last.remove();
      nodes.add(node.value);
      if(node.left != null){
        current.add(node.left);
      }
      if(node.right != null){
        current.add(node.right);
      }
      if(last.isEmpty()){
        lst.add(nodes);
        nodes = new ArrayList<Integer>();
        if(!current.isEmpty()){
          last = current;
          current = new LinkedList<BinaryTreeNode>();
        }
      }
    }
    return lst;
  }
}
