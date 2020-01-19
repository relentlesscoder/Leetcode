package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/18/2016.
 * #0146 https://leetcode.com/problems/lru-cache/
 */
public class LRUCache {

	private Map<Integer, DoublyLinkedListNode> cache = new HashMap<>();
	private int count;
	private int capacity;
	private DoublyLinkedListNode head, tail;

	public LRUCache(int capacity) {
		this.count = 0;
		this.capacity = capacity;
		head = new DoublyLinkedListNode();
		head.prev = null;
		tail = new DoublyLinkedListNode();
		tail.next = null;
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) {
		DoublyLinkedListNode node = cache.get(key);
		if(node == null){
			return -1;
		}
		moveToHead(node);
		return node.value;
	}

	public void put(int key, int value) {
		DoublyLinkedListNode node = cache.get(key);
		if(node == null){
			node = new DoublyLinkedListNode();
			node.key = key;
			node.value = value;
			cache.put(key, node);
			addNode(node);
			if(++count > capacity){
				DoublyLinkedListNode tail = this.popTail();
				cache.remove(tail.key);
				--count;
			}
		}else{
			node.value = value;
			moveToHead(node);
		}
	}

	private void addNode(DoublyLinkedListNode node){
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
	}

	private void removeNode(DoublyLinkedListNode node){
		DoublyLinkedListNode prev = node.prev;
		DoublyLinkedListNode next = node.next;
		prev.next = next;
		next.prev = prev;
	}

	private void moveToHead(DoublyLinkedListNode node){
		removeNode(node);
		addNode(node);
	}

	private DoublyLinkedListNode popTail(){
		DoublyLinkedListNode res = tail.prev;
		removeNode(res);
		return res;
	}

	class DoublyLinkedListNode{
		int key;
		int value;
		DoublyLinkedListNode prev;
		DoublyLinkedListNode next;
	}
}
