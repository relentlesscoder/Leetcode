package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/15/2019.
 * #0759 https://leetcode.com/problems/employee-free-time/
 */
public class EmployeeFreeTime {

	// time O(n*log(n)), space O(n)
	public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
		List<Interval> res = new ArrayList<>();
		PriorityQueue<Interval> pq = new PriorityQueue<>((a, b)
			-> a.start == b.start ? a.end - b.end : a.start - b.start);
		for(List<Interval> s : schedule){
			for(Interval in : s){
				pq.offer(in);
			}
		}
		Interval prev = pq.poll();
		if(prev == null){
			return res;
		}
		while(!pq.isEmpty()){
			Interval cur = pq.poll();
			// skip duplicates
			if(cur.start == prev.start && cur.end == prev.end){
				continue;
			}
			if(cur.start > prev.end){ // the gap between is the common free time
				res.add(new Interval(prev.end, cur.start));
				prev = cur;
			}else{ // if no gap, merge the current
				prev.end = Math.max(prev.end, cur.end);
			}
		}
		return res;
	}

	// time O(n*log(n)), space O(n)
	public List<Interval> employeeFreeTimeTreeMap(List<List<Interval>> schedule) {
		List<Interval> res = new ArrayList<>();
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for(List<Interval> s : schedule){
			for(Interval in : s){
				int start = in.start, end = in.end;
				Integer lower = map.lowerKey(start), ceil = map.floorKey(end);
				if(lower != null && map.get(lower) >= start){
					start = lower.intValue();
				}
				if(ceil != null && map.get(ceil) > end){
					end = map.get(ceil);
				}
				map.subMap(start, true, end, false).clear();
				map.put(start, end);
			}
		}
		int start = Integer.MIN_VALUE;
		for(Map.Entry<Integer, Integer> entry : map.entrySet()){
			if(start != Integer.MIN_VALUE){
				res.add(new Interval(start, entry.getKey()));
			}
			start = entry.getValue();
		}
		return res;
	}
}
