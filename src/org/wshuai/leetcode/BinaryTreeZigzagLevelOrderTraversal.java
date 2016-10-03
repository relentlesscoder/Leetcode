package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/1/2016.
 */
public class BinaryTreeZigzagLevelOrderTraversal {
  public List<List<Integer>> zigzagLevelOrderStack(TreeNode root) {
    List<List<Integer>> lst = new ArrayList<List<Integer>>();
    if(root == null){
      return lst;
    }
    Stack<TreeNode> curr = new Stack<TreeNode>();
    Stack<TreeNode> next = new Stack<TreeNode>();
    curr.push(root);
    List<Integer> ls = new ArrayList<Integer>();
    boolean reverse = false;
    while(!curr.isEmpty()){
      TreeNode node = curr.pop();
      if(reverse){
        if(node.right != null){
          next.push(node.right);
        }
        if(node.left != null){
          next.push(node.left);
        }
      }else{
        if(node.left != null){
          next.push(node.left);
        }
        if(node.right != null){
          next.push(node.right);
        }
      }
      ls.add(node.val);
      if(curr.isEmpty()){
        curr = next;
        next = new Stack<TreeNode>();
        lst.add(ls);
        ls = new ArrayList<Integer>();
        reverse = !reverse;
      }
    }

    return lst;
  }

  public List<List<Integer>> zigzagLevelOrderQueue(TreeNode root) {
    List<List<Integer>> lst = new ArrayList<List<Integer>>();
    if(root == null){
      return lst;
    }
    Queue<TreeNode> curr = new LinkedList<TreeNode>();
    Queue<TreeNode> next = new LinkedList<TreeNode>();
    curr.offer(root);
    List<Integer> ls = new ArrayList<Integer>();
    boolean reverse = false;
    while(!curr.isEmpty()){
      TreeNode node = curr.poll();
      if(node.left != null){
        next.offer(node.left);
      }
      if(node.right != null){
        next.offer(node.right);
      }
      ls.add(node.val);
      if(curr.isEmpty()){
        curr = next;
        next = new LinkedList<TreeNode>();
        if(reverse){
          reverseList(ls);
        }
        lst.add(ls);
        ls = new ArrayList<Integer>();
        reverse = !reverse;
      }
    }

    return lst;
  }

  private void reverseList(List<Integer> ls){
    int len = ls.size();
    int left = 0;
    int right = len - 1;
    while(left < right){
      int lVal = ls.get(left);
      int rVal = ls.get(right);
      ls.set(left, rVal);
      ls.set(right, lVal);
      left++;
      right--;
    }
  }
}
