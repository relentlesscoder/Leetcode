package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/22/2019.
 * #0787 https://leetcode.com/problems/cheapest-flights-within-k-stops/
 */
public class CheapestFlightsWithinKStops {
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		LinkedList<int[]>[] adj = new LinkedList[n];
		for(int i = 0; i < n; i++){
			adj[i] = new LinkedList<>();
		}
		for(int i = 0; i < flights.length; i++){
			adj[flights[i][0]].offerLast(new int[]{flights[i][1], flights[i][2]});
		}
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		Map<Integer, Integer> distance = new HashMap<>();
		queue.offer(new int[]{0, 0, src});
		while(!queue.isEmpty()){
			int[] cur = queue.poll();
			int cost = cur[0], stops = cur[1], city = cur[2];
			if(stops > K + 1 || cost > distance.getOrDefault(stops * 1_000 + city, Integer.MAX_VALUE)){
				continue;
			}
			if(city == dst){
				return cost;
			}
			for(int[] edge : adj[city]){
				int newCost = cost + edge[1];
				if(newCost < distance.getOrDefault((stops + 1) * 1_000 + edge[0], Integer.MAX_VALUE)){
					queue.offer(new int[]{newCost, stops + 1, edge[0]});
					distance.put((stops + 1) * 1_000 + edge[0], newCost);
				}
			}
		}
		return -1;
	}
}


