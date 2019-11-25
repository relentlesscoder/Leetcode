package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/23/19.
 * #815 https://leetcode.com/problems/bus-routes/
 */
public class BusRoutes {
	public int numBusesToDestination(int[][] routes, int S, int T) {
		if(S == T){
			return 0;
		}
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for(int i = 0; i < routes.length; i++){
			for(int r : routes[i]){
				map.putIfAbsent(r, new HashSet<>());
				map.get(r).add(i);
			}
		}
		Set<Integer> visited = new HashSet<>();
		Set<Integer> visitedRoute = new HashSet<>();
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offerLast(S);
		int res = 0;
		while(!queue.isEmpty()){
			int size = queue.size();
			res++;
			while(size-- > 0){
				int p = queue.pollFirst();
				Set<Integer> buses = map.get(p);
				for(int b : buses){
					if(visitedRoute.contains(b)){
						continue;
					}
					visitedRoute.add(b);
					for(int r : routes[b]){
						if(r == T){
							return res;
						}
						if(visited.add(r)){
							queue.offerLast(r);
						}
					}
				}
			}
		}
		return -1;
	}
}
