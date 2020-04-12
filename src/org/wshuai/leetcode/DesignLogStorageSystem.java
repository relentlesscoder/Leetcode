package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 08/03/2017.
 * #0635 https://leetcode.com/problems/design-log-storage-system/
 */
public class DesignLogStorageSystem {

	private String min, max;
	private TreeMap<String, LinkedList<Integer>> logs;
	private Map<String, Integer> units;

	public DesignLogStorageSystem() {
		min = "2000:01:01:00:00:00";
		max = "2017:12:31:23:59:59";
		logs = new TreeMap<>();
		units = new HashMap<String, Integer>(){{
			put("Year", 4);
			put("Month", 7);
			put("Day", 10);
			put("Hour", 13);
			put("Minute", 16);
			put("Second", 19);
		}};
	}

	public void put(int id, String timestamp) {
		logs.putIfAbsent(timestamp, new LinkedList<>());
		logs.get(timestamp).offerLast(id);
	}

	// time O(log(n))
	public List<Integer> retrieve(String s, String e, String gra) {
		LinkedList<Integer> res = new LinkedList<>();
		int index = units.get(gra);
		String start = s.substring(0, index) + min.substring(index);
		String end = e.substring(0, index) + max.substring(index);
		NavigableMap<String, LinkedList<Integer>> sub = logs.subMap(start, true, end, true);
		for(Map.Entry<String, LinkedList<Integer>> entry : sub.entrySet()){
			res.addAll(entry.getValue());
		}
		return res;
	}
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */
