package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/29/19.
 * #1171 https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
 */
public class RemoveZeroSumConsecutiveNodesFromLinkedList {
	// https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/discuss/366319/JavaC%2B%2BPython-Greedily-Skip-with-HashMap
	public LinkedListNode removeZeroSumSublists(LinkedListNode head) {
		int prefix = 0;
		LinkedListNode root = new LinkedListNode(0);
		root.next = head;
		Map<Integer, LinkedListNode> map = new HashMap<>();
		map.put(0, root);
		for(LinkedListNode curr = root; curr != null; curr = curr.next){
			prefix += curr.val;
			// update map with the largest index
			map.put(prefix, curr);
		}
		prefix = 0;
		for(LinkedListNode curr = root; curr != null; curr = curr.next){
			prefix += curr.val;
			curr.next = map.get(prefix).next;
		}
		return root.next;
	}
}
