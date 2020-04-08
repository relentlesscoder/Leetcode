package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 08/20/2019.
 * #0705 https://leetcode.com/problems/design-hashset/
 */
public class DesignHashSet {
	private List<boolean[]> map;
	private boolean tail;

	/**
	 * Initialize your data structure here.
	 */
	public DesignHashSet() {
		map = new ArrayList<>();
		for (int i = 0; i < 1_000; i++) {
			map.add(null);
		}
		tail = false;
	}

	private int[] getIndex(int key) {
		return new int[]{key / 1_000, key % 1_000};
	}

	public void add(int key) {
		if (key == 1_000_000) {
			tail = true;
			return;
		}
		int[] index = getIndex(key);
		boolean[] data = map.get(index[0]);
		if (data == null) {
			map.set(index[0], new boolean[1_000]);
		}
		map.get(index[0])[index[1]] = true;
	}

	public void remove(int key) {
		if (key == 1_000_000) {
			tail = false;
			return;
		}
		int[] index = getIndex(key);
		boolean[] data = map.get(index[0]);
		if (data != null && data[index[1]]) {
			data[index[1]] = false;
		}
	}

	/**
	 * Returns true if this set contains the specified element
	 */
	public boolean contains(int key) {
		if (key == 1_000_000) {
			return tail;
		}
		int[] index = getIndex(key);
		boolean[] data = map.get(index[0]);
		return data != null && data[index[1]];
	}
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
