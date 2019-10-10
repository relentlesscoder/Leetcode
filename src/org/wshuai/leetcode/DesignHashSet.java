package org.wshuai.leetcode;

/**
 * Created by Wei on 8/20/19.
 * #705 https://leetcode.com/problems/design-hashset/
 */
public class DesignHashSet {
	private int[] arr;

	/**
	 * Initialize your data structure here.
	 */
	public DesignHashSet() {
		arr = new int[1000000];
	}

	public void add(int key) {
		arr[key] = 1;
	}

	public void remove(int key) {
		arr[key] = 0;
	}

	/**
	 * Returns true if this set contains the specified element
	 */
	public boolean contains(int key) {
		return arr[key] == 1;
	}
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
