package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 01/27/2020.
 * #1334 https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
 */
public class FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {

	// Dijkstra: time O(n^3 * log(n)), space O(n^2)
	public int findTheCity(int n, int[][] edges, int distanceThreshold) {
		List<int[]>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] e : edges) {
			graph[e[0]].add(new int[]{e[1], e[2]});
			graph[e[1]].add(new int[]{e[0], e[2]});
		}
		int[][] dist = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
			dist[i][i] = 0;
		}
		for (int i = 0; i < n; i++) {
			dijkstra(i, dist[i], graph); // use Dijkstra to find single source shortest path from i to all other nodes
		}
		int minCities = n, minCity = -1;
		for (int i = 0; i < n; i++) {
			int cities = 0;
			for (int j = 0; j < n; j++) {
				cities += (i != j && dist[i][j] <= distanceThreshold ? 1 : 0);
			}
			if (cities <= minCities) {
				minCities = cities;
				minCity = i;
			}
		}
		return minCity;
	}

	// time O(n^2 * log(n)), space O(n^2)
	private void dijkstra(int source, int[] dist, List<int[]>[] graph) {
		PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		minQueue.offer(new int[] {0, source});
		while (!minQueue.isEmpty()) {
			int[] curr = minQueue.poll();
			if (curr[0] > dist[curr[1]]) {
				continue;
			}
			for (int[] next : graph[curr[1]]) {
				if (dist[next[0]] > curr[0] + next[1]) {
					dist[next[0]] = curr[0] + next[1];
					minQueue.offer(new int[] {dist[next[0]], next[0]});
				}
			}
		}
	}
}
