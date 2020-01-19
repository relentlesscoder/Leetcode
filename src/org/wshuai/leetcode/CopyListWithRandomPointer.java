package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/17/2020.
 * #0138 https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {
	// time O(n), space O(n)
	public NodeWithRandomPointer copyRandomList(NodeWithRandomPointer head) {
		if(head == null){
			return null;
		}
		Map<NodeWithRandomPointer, NodeWithRandomPointer> mapping = new HashMap<>();
		NodeWithRandomPointer cur = head;
		while(cur != null){
			mapping.put(cur, new NodeWithRandomPointer(cur.val));
			cur = cur.next;
		}
		cur = head;
		while(cur != null){
			NodeWithRandomPointer copy = mapping.get(cur);
			copy.next = cur.next == null ? null : mapping.get(cur.next);
			copy.random = cur.random == null ? null : mapping.get(cur.random);
			cur = cur.next;
		}
		return mapping.get(head);
	}

	private class NodeWithRandomPointer {
		int val;
		NodeWithRandomPointer next;
		NodeWithRandomPointer random;

		public NodeWithRandomPointer(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}
}
