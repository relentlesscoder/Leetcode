package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/28/2023.
 * #1928 https://leetcode.com/problems/minimum-cost-to-reach-destination-in-time/
 */
public class MinimumCostToReachDestinationInTime {

	// time O(E * log(V)), space O(V + E)
	public int minCost(int maxTime, int[][] edges, int[] passingFees) {
		int n = passingFees.length;
		int[] times = new int[n];
		Arrays.fill(times, Integer.MAX_VALUE);
		List<int[]>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] e : edges) {
			graph[e[0]].add(new int[]{e[1], e[2]});
			graph[e[1]].add(new int[]{e[0], e[2]});
		}
		PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		minQueue.offer(new int[]{0, passingFees[0], 0});
		while (!minQueue.isEmpty()) {
			int[] curr = minQueue.poll();
			int node = curr[0], cost = curr[1], time = curr[2];
			if (time >= times[node] || time > maxTime) {
				continue;
			}
			if (node == n - 1) {
				return cost;
			}
			times[node] = time;
			for (int[] next : graph[node]) {
				int nextNode = next[0], nextTime = time + next[1], nextCost = cost + passingFees[nextNode];
				minQueue.offer(new int[]{nextNode, nextCost, nextTime});
			}
		}
		return -1;
	}
}
