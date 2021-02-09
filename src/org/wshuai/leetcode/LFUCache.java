package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Created by Wei on 09/15/2019.
 * #0460 https://leetcode.com/problems/lfu-cache/
 */
public class LFUCache {

	private int capacity, min;
	private Map<Integer, Integer> dataMap, countMap;
	private Map<Integer, LinkedHashSet<Integer>> keyListMap;

	public LFUCache(int capacity) {
		this.capacity = capacity;
		min = 0;
		// stores key -> value
		dataMap = new HashMap<>();
		// stores key -> frequency
		countMap = new HashMap<>();
		// stores frequency -> keys by insert order
		keyListMap = new HashMap<>();
	}

	public int get(int key) {
		if(!dataMap.containsKey(key)){
			return -1;
		}
		int count = countMap.get(key);
		countMap.put(key, count + 1);
		keyListMap.get(count).remove(key);
		// update min frequency
		if(count == min && keyListMap.get(count).size() == 0){
			min++;
		}
		keyListMap.putIfAbsent(count + 1, new LinkedHashSet<>());
		keyListMap.get(count + 1).add(key);
		return dataMap.get(key);
	}

	public void put(int key, int value) {
		if(capacity <= 0){
			return;
		}
		if(dataMap.containsKey(key)){
			dataMap.put(key, value);
			get(key);
			return;
		}
		if(dataMap.size() == capacity){
			// evict the earliest key with min frequency
			int evict = keyListMap.get(min).iterator().next();
			keyListMap.get(min).remove(evict);
			dataMap.remove(evict);
			countMap.remove(evict);
		}
		dataMap.put(key, value);
		countMap.put(key, 1);
		min = 1;
		keyListMap.putIfAbsent(min, new LinkedHashSet<>());
		keyListMap.get(min).add(key);
	}
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
