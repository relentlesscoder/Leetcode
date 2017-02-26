package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 2/25/17.
 * #449 https://leetcode.com/problems/serialize-and-deserialize-bst/
 */
public class SerializeAndDeserializeBST {
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if(root == null){
      return null;
    }

    StringBuilder sb = new StringBuilder();
    Queue<TreeNode> curr = new LinkedList<TreeNode>();
    Queue<TreeNode> next = new LinkedList<TreeNode>();
    curr.offer(root);
    while(!curr.isEmpty()){
      TreeNode node  = curr.poll();
      if(node != null){
        sb.append(Integer.toString(node.val) + ",");
        next.offer(node.left);
        next.offer(node.right);
      }else{
        sb.append("null,");
      }
      if(curr.isEmpty()){
        curr = next;
        next = new LinkedList<TreeNode>();
      }
    }

    String s = sb.toString();
    return s.substring(0, s.length()-1);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if(data == null || data.isEmpty()){
      return null;
    }
    String[] vals = data.split(",");
    int len = vals.length;
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
    queue.offer(root);
    int i = 1;
    while(i < len && !queue.isEmpty()){
      TreeNode parent = queue.poll();
      String left = vals[i];
      String right = vals[i+1];

      if(left.equals("null")){
        parent.left = null;
      }else{
        TreeNode ln = new TreeNode(Integer.parseInt(left));
        parent.left = ln;
        queue.offer(ln);
      }

      if(right.equals("null")){
        parent.right = null;
      }else{
        TreeNode rn = new TreeNode(Integer.parseInt(right));
        parent.right = rn;
        queue.offer(rn);
      }
      i+=2;
    }
    return root;
  }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
