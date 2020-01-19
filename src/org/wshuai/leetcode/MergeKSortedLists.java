package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 10/11/2016.
 * #0023 https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class MergeKSortedLists {

	// time O(n*log(k)), space O(k), 5ms
	public LinkedListNode mergeKLists(LinkedListNode[] lists) {
		LinkedListNode root = new LinkedListNode(0);
		LinkedListNode cur = root;
		PriorityQueue<LinkedListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
		for(LinkedListNode node : lists){
			if(node == null){
				continue;
			}
			pq.offer(node);
		}
		while(!pq.isEmpty()){
			LinkedListNode node = pq.poll();
			cur.next = node;
			cur = cur.next;
			if(node.next != null){
				pq.offer(node.next);
			}
		}
		return root.next;
	}

	// time O(n*log(k)), space O(1) 2ms
	public LinkedListNode mergeKListsDivideAndConquer(LinkedListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}

		int len = lists.length;
		return mergeKListsUtil(lists, 0, len - 1);
	}

	private LinkedListNode mergeKListsUtil(LinkedListNode[] lists, int p, int q) {
		if (p == q) {
			return lists[p];
		} else {
			int r = p + (q - p) / 2;
			LinkedListNode left = mergeKListsUtil(lists, p, r);
			LinkedListNode right = mergeKListsUtil(lists, r + 1, q);
			return mergeTwoLists(left, right);
		}
	}

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
