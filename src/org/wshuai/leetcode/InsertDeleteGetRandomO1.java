package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 03/27/2017.
 * #0380 https://leetcode.com/problems/insert-delete-getrandom-o1/
 */
public class InsertDeleteGetRandomO1 {
	private Map<Integer, Integer> map;
	private List<Integer> list;
	private Random rand;

	/** Initialize your data structure here. */
	public InsertDeleteGetRandomO1() {
		map = new HashMap<>();
		list = new ArrayList<>();
		rand = new Random();
	}

	/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	public boolean insert(int val) {
		if(map.containsKey(val)){
			return false;
		}
		list.add(val);
		map.put(val, list.size() - 1);
		return true;
	}

	/** Removes a value from the set. Returns true if the set contained the specified element. */
	public boolean remove(int val) {
		if(!map.containsKey(val)){
			return false;
		}
		int index = map.get(val), last = list.get(list.size() - 1);
		if(index != list.size() - 1){
			list.set(index, last);
			map.put(last, index);
		}
		list.remove(list.size() - 1);
		map.remove(val);
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return list.get(rand.nextInt(list.size()));
	}
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
