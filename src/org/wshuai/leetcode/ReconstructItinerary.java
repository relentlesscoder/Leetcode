package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 02/20/2017.
 * #0332 https://leetcode.com/problems/reconstruct-itinerary/
 */
public class ReconstructItinerary {

	// time O(n*log(n)), space O(n)
	public List<String> findItinerary(List<List<String>> tickets) {
		LinkedList<String> res = new LinkedList<>();
		if(tickets == null || tickets.size() == 0){
			return res;
		}
		Map<String, PriorityQueue<String>> map = new HashMap<>();
		for(List<String> t : tickets){
			map.putIfAbsent(t.get(0), new PriorityQueue<>());
			map.get(t.get(0)).offer(t.get(1));
		}
		dfs("JFK", map, res);
		return res;
	}

	private void dfs(String cur, Map<String, PriorityQueue<String>> map, LinkedList<String> res){
		if(!map.containsKey(cur) || map.get(cur).size() == 0){
			res.offerFirst(cur);
			return;
		}
		PriorityQueue<String> pq = map.get(cur);
		while(pq.size() > 0){
			dfs(pq.poll(), map, res);
		}
		res.offerFirst(cur);
	}
}
