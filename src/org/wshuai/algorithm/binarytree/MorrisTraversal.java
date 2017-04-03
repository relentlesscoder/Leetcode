package org.wshuai.algorithm.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 4/1/17.
 * http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
 */
public class MorrisTraversal {

  public List<Integer> preorderTraversal(BinaryTreeNode root){
    List<Integer> lst = new ArrayList<Integer>();

    return lst;
  }

  public List<Integer> inorderTraversal(BinaryTreeNode root){
    List<Integer> lst = new ArrayList<Integer>();

    BinaryTreeNode curr = root;
    BinaryTreeNode prev = null;

    while (curr != null){
      if(curr.left == null){
        lst.add(curr.value);
        curr = curr.right;
      }else{
        //find predecessor
        prev = curr.left;
        while(prev.right != null && prev.right != curr){
          prev = prev.right;
        }

        if(prev.right == null){
          curr = prev.right;
          curr = curr.left;
        }else{
          prev.right = null;
          lst.add(curr.value);
          curr = curr.right;
        }
      }
    }

    return lst;
  }
}
