package org.wshuai.leetcode;

/**
 * Created by Wei on 08/16/2016.
 * #0021 https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedList {
	// time O(m + n)
	public LinkedListNode mergeTwoLists(LinkedListNode l1, LinkedListNode l2) {
		LinkedListNode root = new LinkedListNode(0);
		LinkedListNode cur = root;
		while(l1 != null || l2 != null){
			int v1 = l1 == null ? Integer.MAX_VALUE : l1.val;
			int v2 = l2 == null ? Integer.MAX_VALUE : l2.val;
			if(v1 <= v2){
				cur.next = l1;
				l1 = l1.next;
			}else{
				cur.next = l2;
				l2 = l2.next;
			}
			cur = cur.next;
		}
		return root.next;
	}
}
