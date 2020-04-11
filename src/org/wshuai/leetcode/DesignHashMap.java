package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/18/2019.
 * #0706 https://leetcode.com/problems/design-hashmap/
 */
public class DesignHashMap {
	private List<Integer[]> map;
	private Integer tail;

	/** Initialize your data structure here. */
	public DesignHashMap() {
		map = new ArrayList<>();
		for (int i = 0; i < 1_000; i++) {
			map.add(null);
		}
		tail = null;
	}

	private int[] getIndex(int key) {
		return new int[]{key / 1_000, key % 1_000};
	}

	/** value will always be non-negative. */
	public void put(int key, int value) {
		if (key == 1_000_000) {
			tail = value;
			return;
		}
		int[] index = getIndex(key);
		Integer[] data = map.get(index[0]);
		if (data == null) {
			map.set(index[0], new Integer[1_000]);
		}
		map.get(index[0])[index[1]] = value;
	}

	/** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
	public int get(int key) {
		if (key == 1_000_000) {
			return tail == null ? -1 : tail.intValue();
		}
		int[] index = getIndex(key);
		Integer[] data = map.get(index[0]);
		return data != null && data[index[1]] != null
				? data[index[1]].intValue() : -1;
	}

	/** Removes the mapping of the specified value key if this map contains a mapping for the key */
	public void remove(int key) {
		if (key == 1_000_000) {
			tail = null;
			return;
		}
		int[] index = getIndex(key);
		Integer[] data = map.get(index[0]);
		if (data != null && data[index[1]] != null) {
			data[index[1]] = null;
		}
	}
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
