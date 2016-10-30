package org.wshuai.leetcode;

/**
 * Created by Wei on 1/31/16.
 * #109 https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 */
public class ConvertSortedListToBinarySearchTree {
  public TreeNode sortedListToBST(ListNode head) {
    if(head == null){
      return null;
    }

    int len = 0;
    ListNode curr = head;
    while(curr != null){
      curr = curr.next;
      len++;
    }
    return sortedListToBSTUtil(head, 0, len-1);
  }

  private TreeNode sortedListToBSTUtil(ListNode head, int p, int q){
    if(p > q){
      return null;
    }
    int mid = p + (q-p)/2;
    int s = p;
    ListNode curr = head;
    while(s < mid){
      curr = curr.next;
      s++;
    }
    TreeNode root = new TreeNode(curr.val);
    root.left = sortedListToBSTUtil(head, p, mid-1);
    root.right = sortedListToBSTUtil(curr.next, mid+1, q);
    return root;
  }
}
