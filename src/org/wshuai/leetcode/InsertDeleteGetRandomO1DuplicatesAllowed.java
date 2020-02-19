package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/13/2019.
 * #0381 https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 */
public class InsertDeleteGetRandomO1DuplicatesAllowed {
	private Map<Integer, Set<Integer>> map;
	private List<Integer> list;
	private Random rand;

	/** Initialize your data structure here. */
	public InsertDeleteGetRandomO1DuplicatesAllowed() {
		map = new HashMap<>();
		list = new ArrayList<>();
		rand = new Random();
	}

	/** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
	public boolean insert(int val) {
		boolean res = !map.containsKey(val);
		map.putIfAbsent(val, new HashSet<>());
		map.get(val).add(list.size());
		list.add(val);
		return res;
	}

	/** Removes a value from the collection. Returns true if the collection contained the specified element. */
	public boolean remove(int val) {
		if(!map.containsKey(val) || map.get(val).size() == 0){
			return false;
		}
		Set<Integer> indexes = map.get(val);
		int first = indexes.iterator().next();
		indexes.remove(first);
		int tail = list.get(list.size() - 1);
		list.set(first, tail);
		map.get(tail).add(first);
		map.get(tail).remove(list.size() - 1);
		list.remove(list.size() - 1);
		return true;
	}

	/** Get a random element from the collection. */
	public int getRandom() {
		return list.get(rand.nextInt(list.size()));
	}
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
