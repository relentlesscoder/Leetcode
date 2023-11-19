package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 01/01/2020.
 * #0432 https://leetcode.com/problems/all-oone-data-structure/
 */
public class AllOoneDataStructure {

	private ValueNode head, tail;
	private Map<String, ValueNode> keys;

	/** Initialize your data structure here. */
	public AllOoneDataStructure() {
		head = new ValueNode(0);
		tail = new ValueNode(0);
		head.next = tail;
		tail.prev = head;
		keys = new HashMap<>();
	}

	/** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
	public void inc(String key) {
		ValueNode node = keys.getOrDefault(key, head), next = node.next;
		if(next.val != node.val + 1){
			next = new ValueNode(node.val + 1);
			insert(node.next, next);
		}
		next.strs.add(key);
		keys.put(key, next);
		if(node != head){
			remove(node, key);
		}
	}

	/** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
	public void dec(String key) {
		ValueNode node = keys.get(key);
		if(node == null){
			return;
		}
		if(node.val == 1){
			keys.remove(key);
			remove(node, key);
			return;
		}
		ValueNode prev = node.prev;
		if(prev.val != node.val - 1){
			prev = new ValueNode(node.val - 1);
			insert(node, prev);
		}
		prev.strs.add(key);
		keys.put(key, prev);
		remove(node, key);
	}

	/** Returns one of the keys with maximal value. */
	public String getMaxKey() {
		if(tail.prev == head){
			return "";
		}
		return tail.prev.strs.iterator().next();
	}

	/** Returns one of the keys with Minimal value. */
	public String getMinKey() {
		if(head.next == tail){
			return "";
		}
		return head.next.strs.iterator().next();
	}

	private void remove(ValueNode node, String key){
		ValueNode prev = node.prev, next = node.next;
		node.strs.remove(key);
		if(node.strs.isEmpty()){
			prev.next = next;
			next.prev = prev;
		}
	}

	private void insert(ValueNode next, ValueNode node){
		ValueNode prev = next.prev;
		prev.next = node;
		node.prev = prev;
		next.prev = node;
		node.next = next;
	}

	private class ValueNode{

		private ValueNode prev, next;
		private int val;
		private Set<String> strs;

		private ValueNode(int val){
			this.val = val;
			strs = new HashSet<>();
			prev = null;
			next = null;
		}
	}
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
