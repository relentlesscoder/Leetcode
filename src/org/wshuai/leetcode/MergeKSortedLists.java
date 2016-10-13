package org.wshuai.leetcode;

/**
 * Created by Wei on 10/11/2016.
 * #23 https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class MergeKSortedLists {
  public ListNode mergeKLists(ListNode[] lists) {
    if(lists == null || lists.length == 0){
      return null;
    }
    if(lists.length == 1){
      return lists[0];
    }
    ListNode x = mergeLists(lists, 0, lists.length - 1);
    return x;
  }

  public ListNode mergeLists(ListNode[] lst, int p, int q){
    if(q - p > 1){
      int r = (p + q)/2;
      ListNode left = mergeLists(lst, p, r);
      ListNode right = mergeLists(lst, r+1, q);
      MergeTwoSortedList mt = new MergeTwoSortedList();
      return mt.mergeTwoLists(left, right);
    }else if(q == p + 1){
      MergeTwoSortedList mt = new MergeTwoSortedList();
      return mt.mergeTwoLists(lst[p], lst[q]);
    }else{
      return lst[p];
    }
  }
}
