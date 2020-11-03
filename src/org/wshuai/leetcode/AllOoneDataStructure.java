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

	private Map<String, Integer> map;
	private Map<Integer, Set<String>> vals;
	String maxKey;
	String minKey;
	int max;
	int min;

	/** Initialize your data structure here. */
	// https://leetcode.com/problems/all-oone-data-structure/discuss/91400/All-in-O(1)-with-detailed-explantation
	public AllOoneDataStructure() {
		map = new HashMap<>();
		vals = new HashMap<>();
		maxKey = "";
		minKey = "";
		max = 0;
		min = 0;
	}

	/** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
	public void inc(String key) {
		int val = map.getOrDefault(key, 0) + 1;
		map.put(key, val);
		vals.putIfAbsent(val, new HashSet<>());
		vals.get(val).add(key);
		if(vals.containsKey(val - 1)){
			vals.get(val - 1).remove(key);
			if(vals.get(val - 1).size() == 0){
				vals.remove(val - 1);
			}
		}
		if(val > max){
			max = val;
			maxKey = key;
		}
		if(val - 1 == min){
			if(vals.get(min) == null || vals.get(min).size() == 0){
				min++;
				minKey = key;
			}else{
				minKey = vals.get(min).iterator().next();
			}
		}
		if(val == 1){
			min = 1;
			minKey = key;
		}
	}

	/** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
	public void dec(String key) {
		if(map.containsKey(key)){
			if(map.get(key) == 1){
				map.remove(key);
				vals.get(1).remove(key);
				if(vals.get(1).size() > 0){
					min = 1;
					minKey = vals.get(1).iterator().next();
					if(max == 1){
						maxKey = minKey;
					}
				}else{
					vals.remove(1);
					if(map.size() > 0){
						int tempMin = Integer.MAX_VALUE;
						for(Map.Entry<Integer, Set<String>> entry : vals.entrySet()){
							if(entry.getValue().size() > 0){
								tempMin = Math.min(tempMin, entry.getKey());
							}
						}
						min = tempMin;
						minKey = vals.get(min).iterator().next();
					}else{
						min = 0;
						max = 0;
					}
				}
			}else{
				map.put(key, map.get(key) - 1);
				int val = map.get(key);
				vals.get(val + 1).remove(key);
				if(vals.get(val + 1).size() == 0){
					vals.remove(val + 1);
				}
				if(vals.get(val) == null){
					vals.put(val, new HashSet<>());
				}
				vals.get(val).add(key);
				if(val + 1 == max){
					if(vals.get(max) == null || vals.get(max).size() == 0){
						max--;
					}else{
						maxKey = vals.get(max).iterator().next();
					}
				}
				if(val + 1 == min){
					min--;
					minKey = key;
				}
			}
		}
	}

	/** Returns one of the keys with maximal value. */
	public String getMaxKey() {
		if(map.size() == 0){
			return "";
		}
		return maxKey;
	}

	/** Returns one of the keys with Minimal value. */
	public String getMinKey() {
		if(map.size() == 0){
			return "";
		}
		return minKey;
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
