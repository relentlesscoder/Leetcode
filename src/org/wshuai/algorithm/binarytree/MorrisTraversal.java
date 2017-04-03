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
          lst.add(curr.value);
          prev.right = curr;
          curr = curr.left;
        }else{
          prev.right = null;
          curr = curr.right;
        }
      }
    }

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
          prev.right = curr;
          curr = curr.left;
        }else{
          lst.add(curr.value);
          prev.right = null;
          curr = curr.right;
        }
      }
    }

    return lst;
  }

  public List<Integer> postorderTraversal(BinaryTreeNode root){
    List<Integer> lst = new ArrayList<Integer>();
    BinaryTreeNode dump = new BinaryTreeNode(0);
    dump.left = root;
    BinaryTreeNode curr = dump;
    BinaryTreeNode prev = null;

    while(curr != null){
      if(curr.left == null){
        curr = curr.right;
      }else{
        //find predecessor
        prev = curr.left;
        while(prev.right != null && prev.right != curr){
          prev = prev.right;
        }

        if(prev.right == null){
          prev.right = curr;
          curr = curr.left;
        }else{
          printReverse(curr.left, prev, lst);
          prev.right = null;
          curr = curr.right;
        }
      }
    }
    return lst;
  }

  private void printReverse(BinaryTreeNode from, BinaryTreeNode to, List<Integer> lst){
    reverse(from, to);

    BinaryTreeNode node = to;
    while(true){
      lst.add(node.value);
      if(node == from){
        break;
      }
      node = node.right;
    }

    reverse(to, from);
  }

  private void reverse(BinaryTreeNode from, BinaryTreeNode to){
    if(from == to){
      return;
    }
    BinaryTreeNode x = from;
    BinaryTreeNode y = to;
    BinaryTreeNode z = null;
    while (true){
      z = y.right;
      y.right = x;
      x = y;
      y = z;
      if(x == to){
        break;
      }
    }
  }
}
