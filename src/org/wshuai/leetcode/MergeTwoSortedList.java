package org.wshuai.leetcode;

/**
 * Created by Wei on 8/16/2016.
 */
public class MergeTwoSortedList {
  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if(l1 == null && l2 == null){
      return null;
    }
    if(l1 == null){
      return l2;
    }
    if(l2 == null){
      return l1;
    }
    ListNode current = l1.val < l2.val ? l1 : l2;
    ListNode root = current;
    while(true){

      while(l1 != null && l1.val < l2.val){
        current = l1;
        l1 = l1.next;
      }
      current.next = l2;
      if(l1 == null){
        break;
      }

      while(l2 != null && l2.val <= l1.val){
        current = l2;
        l2 = l2.next;
      }
      current.next = l1;
      if(l2 == null){
        break;
      }
    }

    return root;
  }
}
