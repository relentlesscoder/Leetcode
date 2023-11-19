package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/22/2019.
 * #0787 https://leetcode.com/problems/cheapest-flights-within-k-stops/
 */
public class CheapestFlightsWithinKStops {

	// time O(n^k*log(n^k)), space O(n^k)
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		List<int[]>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] f : flights) {
			graph[f[0]].add(new int[]{f[1], f[2]});
		}
		int[] stops = new int[n];
		Arrays.fill(stops, Integer.MAX_VALUE);
		PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		minQueue.offer(new int[]{src, 0, 0});
		while (!minQueue.isEmpty()) {
			int[] curr = minQueue.poll();
			int u = curr[0], cost = curr[1], stop = curr[2];
			if (stop > stops[u] || stop > k + 1){
				continue;
			}
			if (u == dst) {
				return cost;
			}
			stops[u] = stop;
			for (int[] next : graph[u]) {
				int v = next[0], vc = next[1];
				minQueue.offer(new int[]{v, cost + vc, stop + 1});
			}
		}
		return -1;
	}

	// time O(n*k), space O(n*k)
	public int findCheapestPriceBellmanFord(int n, int[][] flights, int src, int dst, int K) {
		int[] cost = new int[n];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[src] = 0;
		for (int i = 0; i <= K; i++) {
			// use new array to move one step each time
			int[] temp = Arrays.copyOf(cost, n);
			for (int[] f : flights) {
				int cur = f[0], next = f[1], price = f[2];
				if (cost[cur] == Integer.MAX_VALUE) {
					continue;
				}
				temp[next] = Math.min(temp[next], cost[cur] + price);
			}
			cost = temp;
		}
		return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
	}
}


