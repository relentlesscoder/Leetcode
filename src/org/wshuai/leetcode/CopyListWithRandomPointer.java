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
		Map<NodeWithRandomPointer, NodeWithRandomPointer> map = new HashMap<>();
		return dfs(head, map);
	}

	private NodeWithRandomPointer dfs(NodeWithRandomPointer node,
	                                  Map<NodeWithRandomPointer, NodeWithRandomPointer> map){
		if(node == null){
			return null;
		}
		if(map.containsKey(node)){
			return map.get(node);
		}
		NodeWithRandomPointer copy = new NodeWithRandomPointer(node.val);
		map.put(node, copy);
		copy.next = dfs(node.next, map);
		copy.random = dfs(node.random, map);
		return copy;
	}

	// time O(n), space O(n)
	public NodeWithRandomPointer copyRandomListTwoPass(NodeWithRandomPointer head) {
		if(head == null){
			return null;
		}
		Map<NodeWithRandomPointer, NodeWithRandomPointer> map = new HashMap<>();
		NodeWithRandomPointer cur = head;
		while(cur != null){
			map.put(cur, new NodeWithRandomPointer(cur.val));
			cur = cur.next;
		}
		cur = head;
		while(cur != null){
			if(cur.next != null){
				map.get(cur).next = map.get(cur.next);
			}
			if(cur.random != null){
				map.get(cur).random = map.get(cur.random);
			}
			cur = cur.next;
		}
		return map.get(head);
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
