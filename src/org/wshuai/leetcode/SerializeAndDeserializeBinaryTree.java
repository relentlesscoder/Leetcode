package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 10/2/2016.
 * #297 https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
public class SerializeAndDeserializeBinaryTree {
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if(root == null){
      return "";
    }

    StringBuilder sb = new StringBuilder();
    Queue<TreeNode> curr = new LinkedList<TreeNode>();
    Queue<TreeNode> next = new LinkedList<TreeNode>();
    curr.offer(root);
    while(!curr.isEmpty()){
      TreeNode tn = curr.poll();
      if(tn != null){
        next.offer(tn.left);
        next.offer(tn.right);
        sb.append(tn.val + ",");
      }else{
        sb.append("null,");
      }

      if(curr.isEmpty()){
        curr = next;
        next = new LinkedList<TreeNode>();
      }
    }

    String val = sb.toString();
    return val.substring(0, val.length() - 1);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if(data == null || data.isEmpty()){
      return null;
    }
    String[] arr = data.split(",");
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
    queue.offer(root);
    int len = arr.length;
    int i = 1;
    while(i < len && !queue.isEmpty()){
      TreeNode parent = queue.poll();
      String lVal = arr[i];
      String rVal = arr[i+1];
      if(lVal.equals("null")){
        parent.left = null;
      }else{
        TreeNode left = new TreeNode(Integer.parseInt(lVal));
        parent.left = left;
        queue.offer(left);
      }

      if(rVal.equals("null")){
        parent.right = null;
      }else{
        TreeNode right = new TreeNode(Integer.parseInt(rVal));
        parent.right = right;
        queue.offer(right);
      }

      i = i+2;
    }
    return root;
  }
}
