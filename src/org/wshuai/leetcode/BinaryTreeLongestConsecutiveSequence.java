package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 10/28/2016.
 * #298 https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
 */
public class BinaryTreeLongestConsecutiveSequence {
  public int longestConsecutiveRecursive(TreeNode root) {
    RefType res = new RefType();
    longestConsecutiveUtil(root, res);
    return res.val;
  }

  public int longestConsecutiveUtil(TreeNode root, RefType res){
    if(root == null){
      return 0;
    }

    if(root.left==null && root.right==null){
      res.val = 1 > res.val ? 1 : res.val;
      return 1;
    }

    int lVal = longestConsecutiveUtil(root.left, res);
    int left = root.left != null && root.val == root.left.val-1 ? lVal+1 : 1;

    int rVal = longestConsecutiveUtil(root.right, res);
    int right = root.right != null && root.val == root.right.val-1 ? rVal+1 : 1;

    int cmax = Math.max(left, right);
    res.val = cmax > res.val ? cmax : res.val;
    return cmax;
  }

  //Queue
  public int longestConsecutiveIterative(TreeNode root) {
    if(root == null){
      return 0;
    }

    int max = 1;
    Queue<TreeNode> nodes = new LinkedList<TreeNode>();
    Queue<Integer> sizes = new LinkedList<Integer>();
    nodes.offer(root);
    sizes.offer(1);
    while(!nodes.isEmpty()){
      TreeNode node = nodes.poll();
      int size = sizes.poll();
      if(node.left != null){
        int ns = 1;
        if(node.val == node.left.val-1){
          ns = size+1;
          max = ns > max ? ns : max;
        }
        sizes.offer(ns);
        nodes.offer(node.left);
      }

      if(node.right != null){
        int ns = 1;
        if(node.val == node.right.val-1){
          ns = size+1;
          max = ns > max ? ns : max;
        }
        sizes.offer(ns);
        nodes.offer(node.right);
      }
    }

    return max;
  }
}
