package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/23/2019.
 * #0815 https://leetcode.com/problems/bus-routes/
 */
public class BusRoutes {
	// time O(m + +n), space O(m + n)
	public int numBusesToDestination(int[][] routes, int S, int T) {
		if(S == T){
			return 0;
		}
		Map<Integer, Set<Integer>> stops = new HashMap<>();
		for(int i = 0; i < routes.length; i++){
			for(int s : routes[i]){
				stops.putIfAbsent(s, new HashSet<>());
				stops.get(s).add(i);
			}
		}
		int buses = 0;
		Set<Integer> visitedRoute = new HashSet<>(), visitedStop = new HashSet<>();
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offerLast(S);
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int cur = queue.pollFirst();
				for(int route : stops.get(cur)){
					if(visitedRoute.contains(route)){
						continue;
					}
					visitedRoute.add(route);
					for(int next : routes[route]){
						if(next == T){
							return buses + 1;
						}
						if(visitedStop.add(next)){
							queue.offerLast(next);
						}
					}
				}
			}
			buses++;
		}
		return -1;
	}
}
