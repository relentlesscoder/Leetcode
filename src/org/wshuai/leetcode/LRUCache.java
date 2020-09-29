package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/18/2016.
 * #0146 https://leetcode.com/problems/lru-cache/
 */
public class LRUCache {

	private int capacity;

	private DoublyLinkedListNode head;

	private DoublyLinkedListNode tail;

	private Map<Integer, DoublyLinkedListNode> map;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<>();
		head = new DoublyLinkedListNode(-1, -1);
		tail = new DoublyLinkedListNode(-1, -1);
		head.next = tail;
		tail.prev = head;
	}

	// time O(1)
	public int get(int key) {
		int res = -1;
		if(map.containsKey(key)){
			DoublyLinkedListNode cur = map.get(key);
			res = cur.val;
			remove(cur);
			insertToFront(cur);
		}
		return res;
	}

	// time O(1)
	public void put(int key, int value) {
		if(map.containsKey(key)){
			DoublyLinkedListNode cur = map.get(key);
			cur.val = value;
			remove(cur);
			insertToFront(cur);
		}else{
			DoublyLinkedListNode cur = new DoublyLinkedListNode(value, key);
			map.put(key, cur);
			insertToFront(cur);
			// evict the least used entry
			if(map.size() > capacity){
				DoublyLinkedListNode last = tail.prev;
				map.remove(last.key);
				remove(last);
			}
		}
	}

	private void insertToFront(DoublyLinkedListNode node){
		DoublyLinkedListNode after = head.next;
		node.prev = head;
		head.next = node;
		node.next = after;
		after.prev = node;
	}

	private void remove(DoublyLinkedListNode node){
		DoublyLinkedListNode prev = node.prev;
		DoublyLinkedListNode next = node.next;
		prev.next = next;
		next.prev = prev;
	}

	private class DoublyLinkedListNode{

		private int val;

		private int key;

		private DoublyLinkedListNode prev;

		private DoublyLinkedListNode next;

		private DoublyLinkedListNode(int val, int key){
			this.val = val;
			this.key = key;
			prev = null;
			next = null;
		}
	}
}
