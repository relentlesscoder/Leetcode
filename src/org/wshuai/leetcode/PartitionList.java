package org.wshuai.leetcode;

/**
 * Created by Wei on 11/8/16.
 * #86 https://leetcode.com/problems/partition-list/
 */
public class PartitionList {
  //O(n)
  public ListNode partition(ListNode head, int x) {
    ListNode left = new ListNode(-1);
    ListNode right = new ListNode(-1);
    ListNode lcurr = left;
    ListNode rcurr = right;
    while(head != null){
      if(head.val < x){
        ListNode node = new ListNode(head.val);
        lcurr.next = node;
        lcurr = node;
      }else{
        ListNode node = new ListNode(head.val);
        rcurr.next = node;
        rcurr = node;
      }
      head = head.next;
    }
    if(left.next == null){
      return right.next;
    }else{
      lcurr.next = right.next;
      return left.next;
    }
  }
}
