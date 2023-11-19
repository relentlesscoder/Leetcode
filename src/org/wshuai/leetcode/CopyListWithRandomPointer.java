package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/17/2020.
 * #0138 https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {

	// time O(n), space O(n)
	public Node copyRandomList(Node head) {
		Map<Node, Node> map = new HashMap<>();
		return dfs(head, map);
	}

	private Node dfs(Node curr, Map<Node, Node> map) {
		if (curr == null) {
			return curr;
		}
		if (map.containsKey(curr)) {
			return map.get(curr);
		}
		Node copy = new Node(curr.val);
		map.put(curr, copy);
		copy.next = dfs(curr.next, map);
		copy.random = dfs(curr.random, map);
		return copy;
	}

	/**
	 * Definition for a Node.
	 */
	private class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}
}
