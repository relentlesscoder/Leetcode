package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/28/2023.
 * #1976 https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
 */
public class NumberOfWaysToArriveAtDestination {

	// time O(E * log(V)), space O(V + E)
	public int countPaths(int n, int[][] roads) {
		List<int[]>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] e : roads) {
			graph[e[0]].add(new int[] {e[1], e[2]});
			graph[e[1]].add(new int[] {e[0], e[2]});
		}
		return dijkstra(graph, n, 0);
	}

	private int dijkstra(List<int[]>[] graph, int n, int src) {
		long[] dist = new long[n], ways = new long[n];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[src] = 0;
		ways[src] = 1;
		PriorityQueue<long[]> minQueue = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
		minQueue.offer(new long[] {0, src});
		while (!minQueue.isEmpty()) {
			long[] curr = minQueue.poll();
			long distance = curr[0];
			int u = (int)curr[1];
			if (distance > dist[u]) {
				continue;
			}
			for (int[] next : graph[u]) {
				int v = next[0], cost = next[1];
				if (dist[v] > distance + cost) {
					dist[v] = distance + cost;
					ways[v] = ways[u];
					minQueue.offer(new long[]{dist[v], v});
				} else if (dist[v] == distance + cost) {
					ways[v] = (ways[v] + ways[u]) % 1_000_000_007;
				}
			}
		}
		return (int)ways[n - 1];
	}
}
