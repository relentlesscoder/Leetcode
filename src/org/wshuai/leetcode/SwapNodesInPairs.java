package org.wshuai.leetcode;

/**
 * Created by Wei on 01/05/2020.
 * #0024 https://leetcode.com/problems/swap-nodes-in-pairs/
 */
public class SwapNodesInPairs {
	// time O(n)
	public LinkedListNode swapPairs(LinkedListNode head) {
		LinkedListNode root = new LinkedListNode(0);
		root.next = head;
		LinkedListNode prev = root;
		LinkedListNode cur = head;
		while(cur != null){
			LinkedListNode next = cur.next;
			if(next == null){
				break;
			}else{
				prev.next = next;
				LinkedListNode temp = next.next;
				next.next = cur;
				cur.next = temp;
				prev = cur;
				cur = temp;
			}
		}
		return root.next;
	}
}
