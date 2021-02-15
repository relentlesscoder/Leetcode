package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/23/2019.
 * #0815 https://leetcode.com/problems/bus-routes/
 */
public class BusRoutes {

	// time O(m*n), space O(m*n)
	public int numBusesToDestination(int[][] routes, int source, int target) {
		if(source == target){
			return 0;
		}
		int buses = 0;
		Map<Integer, Set<Integer>> stopToRoute = new HashMap<>();
		for(int i = 0; i < routes.length; i++){
			for(int stop : routes[i]){
				stopToRoute.putIfAbsent(stop, new HashSet<>());
				stopToRoute.get(stop).add(i);
			}
		}
		Set<Integer> visitedRoute = new HashSet<>(), visitedStop = new HashSet<>();
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offerLast(source);
		visitedStop.add(source);
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int curStop = queue.pollFirst();
				for(int nextRoute : stopToRoute.get(curStop)){
					if(visitedRoute.contains(nextRoute)){
						continue;
					}
					visitedRoute.add(nextRoute);
					for(int nextStop : routes[nextRoute]){
						if(nextStop == target){
							return buses + 1;
						}
						if(visitedStop.add(nextStop)){
							queue.offerLast(nextStop);
						}
					}
				}
			}
			buses++;
		}
		return -1;
	}
}
