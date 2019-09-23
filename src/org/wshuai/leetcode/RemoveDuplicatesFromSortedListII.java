package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/16.
 * #82 https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class RemoveDuplicatesFromSortedListII {
  public LinkedListNode deleteDuplicates(LinkedListNode head) {
    if(head == null){
      return null;
    }

    LinkedListNode root = new LinkedListNode(-1);
    LinkedListNode prev = root;
    LinkedListNode curr = head;
    while(curr != null){
      int val = curr.val;
      LinkedListNode nxt = curr.next;
      while(nxt != null && nxt.val == val){
        nxt = nxt.next;
      }
      if(curr.next == nxt){
        prev.next = curr;
        prev = curr;
        curr = curr.next;
      }else{
        if(nxt == null){
          prev.next = null;
        }
        curr = nxt;
      }
    }

    return root.next;
  }
}
