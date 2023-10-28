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
		List<int[]>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int[] e : edges) {
			graph[e[0]].add(new int[]{e[1], e[2]});
			graph[e[1]].add(new int[]{e[0], e[2]});
		}
		int[] cost = new int[n], time = new int[n];
		Arrays.fill(cost, Integer.MAX_VALUE);
		Arrays.fill(time, Integer.MAX_VALUE);
		cost[0] = passingFees[0];
		time[0] = 0;
		PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]);
		minQueue.offer(new int[] {0, passingFees[0], 0});
		while (!minQueue.isEmpty()) {
			int[] curr = minQueue.poll();
			int u = curr[0], uc = curr[1], ut = curr[2];
			for (int[] next : graph[u]) {
				int v = next[0], vt = next[1];
				if (ut + vt > maxTime) {
					continue;
				}
				if (cost[v] > uc + passingFees[v]) {
					cost[v] = uc + passingFees[v];
					time[v] = ut + vt;
					minQueue.offer(new int[] {v, uc + passingFees[v], ut + vt});
				} else if (time[v] > ut + vt) {
					time[v] = ut + vt;
					minQueue.offer(new int[] {v, uc + passingFees[v], ut + vt});
				}
			}
		}
		return cost[n - 1] == Integer.MAX_VALUE ? -1 : cost[n - 1];
	}
}
