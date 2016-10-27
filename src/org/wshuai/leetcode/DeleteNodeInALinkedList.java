package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #237 https://leetcode.com/problems/delete-node-in-a-linked-list/
 */
public class DeleteNodeInALinkedList {
  public void deleteNode(ListNode node) {
    if(node == null){
      return;
    }
    if(node.next == null){
      node = null;
    }
    node.val = node.next.val;
    node.next = node.next.next;
  }
}
