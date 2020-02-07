package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 02/20/2017.
 * #0332 https://leetcode.com/problems/reconstruct-itinerary/
 */
public class ReconstructItinerary {
	public List<String> findItinerary(List<List<String>> tickets) {
		LinkedList<String> res = new LinkedList<>();
		if(tickets == null || tickets.size() == 0){
			return res;
		}
		Map<String, PriorityQueue<String>> adj = new HashMap<>();
		for(List<String> t : tickets){
			String from = t.get(0), to = t.get(1);
			adj.putIfAbsent(from, new PriorityQueue<>());
			adj.get(from).add(to);
		}
		dfs("JFK", adj, res);
		return res;
	}

	private void dfs(String cur, Map<String, PriorityQueue<String>> adj, LinkedList<String> res){
		if(!adj.containsKey(cur) || adj.get(cur).isEmpty()){
			res.offerFirst(cur);
			return;
		}
		PriorityQueue<String> pq = adj.get(cur);
		while(!pq.isEmpty()){
			String next = pq.poll();
			dfs(next, adj, res);
		}
		res.offerFirst(cur);
	}
}
