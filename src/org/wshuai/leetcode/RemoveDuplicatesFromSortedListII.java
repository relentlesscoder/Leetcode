package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/16.
 * #82 https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class RemoveDuplicatesFromSortedListII {
  public ListNode deleteDuplicates(ListNode head) {
    if(head == null){
      return null;
    }

    ListNode root = new ListNode(-1);
    ListNode prev = root;
    ListNode curr = head;
    while(curr != null){
      int val = curr.val;
      ListNode nxt = curr.next;
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
