package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/18/2016.
 * #0146 https://leetcode.com/problems/lru-cache/
 */
public class LRUCache {

	private class LRUCacheDoublyLinkedList {

		private int capacity;
		private Map<Integer, DoublyLinkedListNode> map;
		private DoublyLinkedListNode head, tail;

		public LRUCacheDoublyLinkedList(int capacity) {
			this.capacity = capacity;
			this.map = new HashMap<>();
			this.head = new DoublyLinkedListNode(-1, -1);
			this.tail = new DoublyLinkedListNode(-1, -1);
			head.next = tail;
			tail.prev = head;
		}

		// time O(1), space O(n)
		public int get(int key) {
			if (!map.containsKey(key)) {
				return -1;
			}
			DoublyLinkedListNode node = map.get(key);
			remove(node);
			insertToFront(node);
			return node.value;
		}

		// time O(1), space O(n)
		public void put(int key, int value) {
			if (map.containsKey(key)) {
				DoublyLinkedListNode node = map.get(key);
				node.value = value;
				remove(node);
				insertToFront(node);
			} else {
				DoublyLinkedListNode node = new DoublyLinkedListNode(key, value);
				map.put(key, node);
				insertToFront(node);
				if (map.size() > this.capacity) {
					DoublyLinkedListNode leastUsedNode = this.tail.prev;
					map.remove(leastUsedNode.key);
					remove(leastUsedNode);
				}
			}
		}

		private void insertToFront(DoublyLinkedListNode node) {
			DoublyLinkedListNode next = head.next;
			head.next = node;
			node.prev = head;
			node.next = next;
			next.prev = node;
		}

		private void remove(DoublyLinkedListNode node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			node.prev = null;
			node.next = null;
		}

		private class DoublyLinkedListNode {

			private int key;
			private int value;
			private DoublyLinkedListNode prev;
			private DoublyLinkedListNode next;

			public DoublyLinkedListNode(int key, int value) {
				this.key = key;
				this.value = value;
				this.prev = null;
				this.next = null;
			}
		}
	}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
