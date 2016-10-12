package org.wshuai.leetcode;

/**
 * Created by Wei on 8/16/2016.
 * #21 https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedList {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if(l1 == null){
      return l2;
    }
    if(l2 == null){
      return l1;
    }

    ListNode root = new ListNode(0);
    ListNode c = root;
    while(l1 != null || l2 != null){
      if(l1 == null){
        c.next = l2;
        break;
      }
      if(l2 == null){
        c.next = l1;
        break;
      }
      if(l1.val < l2.val){
        c.next = l1;
        l1 = l1.next;
      }else{
        c.next = l2;
        l2 = l2.next;
      }
      c = c.next;
    }

    return root.next;
  }
}
