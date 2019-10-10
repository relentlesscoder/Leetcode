package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wei on 9/22/19.
 * #787 https://leetcode.com/problems/cheapest-flights-within-k-stops/
 */
public class CheapestFlightsWithinKStops {
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		LinkedList<Edge>[] adj = new LinkedList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new LinkedList<Edge>();
		}
		for (int j = 0; j < flights.length; j++) {
			adj[flights[j][0]].add(new Edge(flights[j][0], flights[j][1], flights[j][2]));
		}
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		Map<Integer, Integer> distance = new HashMap<>();
		queue.offer(new int[]{0, 0, src});
		while (!queue.isEmpty()) {
			int[] arr = queue.poll();
			int cost = arr[0];
			int stop = arr[1];
			int city = arr[2];
			if (stop > K + 1 || cost > distance.getOrDefault(stop * 1000 + city, Integer.MAX_VALUE)) {
				continue;
			}
			if (city == dst) {
				return cost;
			}
			for (Edge edge : adj[city]) {
				int newCost = cost + edge.weight;
				if (newCost < distance.getOrDefault((stop + 1) * 1000 + edge.w, Integer.MAX_VALUE)) {
					queue.offer(new int[]{newCost, stop + 1, edge.w});
					distance.put((stop + 1) * 1000 + edge.w, newCost);
				}
			}
		}
		return -1;
	}
}


