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
		if(lists == null || lists.length == 0){
			return null;
		}
		return mergeLists(lists, 0, lists.length - 1);
	}

	private LinkedListNode mergeLists(LinkedListNode[] lists, int i, int j){
		if(i == j){
			return lists[i];
		}else{
			int k = i + (j - i) / 2;
			LinkedListNode a = mergeLists(lists, i, k);
			LinkedListNode b = mergeLists(lists, k + 1, j);
			return merge(a, b);
		}
	}

	private LinkedListNode merge(LinkedListNode a, LinkedListNode b){
		LinkedListNode root = new LinkedListNode(-1), cur = root, next = null;
		while(a != null || b != null){
			int v1 = a != null ? a.val : Integer.MAX_VALUE;
			int v2 = b != null ? b.val : Integer.MAX_VALUE;
			if(v1 <= v2){
				cur.next = a;
				a = a.next;
			}else{
				cur.next = b;
				b = b.next;
			}
			cur = cur.next;
		}
		return root.next;
	}
}
