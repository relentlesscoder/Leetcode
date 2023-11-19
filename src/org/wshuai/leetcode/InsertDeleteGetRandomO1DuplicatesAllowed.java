package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/13/2019.
 * #0381 https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 */
public class InsertDeleteGetRandomO1DuplicatesAllowed {

	private Map<Integer, Set<Integer>> position;
	private List<Integer> data;
	private Random random;

	/** Initialize your data structure here. */
	public InsertDeleteGetRandomO1DuplicatesAllowed() {
		position = new HashMap<>();
		data = new ArrayList<>();
		random = new Random();
	}

	/** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
	public boolean insert(int val) {
		boolean res = !position.containsKey(val);
		data.add(val);
		position.putIfAbsent(val, new HashSet<>());
		position.get(val).add(data.size() - 1);
		return res;
	}

	/** Removes a value from the collection. Returns true if the collection contained the specified element. */
	public boolean remove(int val) {
		if(!position.containsKey(val)
				|| position.get(val).size() == 0){
			return false;
		}
		Set<Integer> set = position.get(val);
		int tail = data.get(data.size() - 1);
		if(tail == val){
			set.remove(data.size() - 1);
		}else{
			int index = set.iterator().next();
			data.set(index, tail);
			set.remove(index);
			position.get(tail).remove(data.size() - 1);
			position.get(tail).add(index);
		}
		data.remove(data.size() - 1);
		return true;
	}

	/** Get a random element from the collection. */
	public int getRandom() {
		return data.get(random.nextInt(data.size()));
	}
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
