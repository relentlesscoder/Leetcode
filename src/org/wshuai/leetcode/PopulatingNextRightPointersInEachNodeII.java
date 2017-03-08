package org.wshuai.leetcode;

/**
 * Created by Wei on 3/7/17.
 * #117 https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 */
public class PopulatingNextRightPointersInEachNodeII {
  public void connect(TreeLinkNode root) {
    if(root == null){
      return;
    }
    if(root.left != null){
      if(root.right != null){
        root.left.next = root.right;
      }else{
        TreeLinkNode nxt = root.next;
        while(nxt != null){
          if(nxt.left != null){
            root.left.next = nxt.left;
            break;
          }else if(nxt.right != null){
            root.left.next = nxt.right;
            break;
          }
          nxt = nxt.next;
        }
      }
    }
    if(root.right != null){
      TreeLinkNode nxt = root.next;
      while(nxt != null){
        if(nxt.left != null){
          root.right.next = nxt.left;
          break;
        }else if(nxt.right != null){
          root.right.next = nxt.right;
          break;
        }
        nxt = nxt.next;
      }
    }
    //Right node first!
    connect(root.right);
    connect(root.left);
  }
}
