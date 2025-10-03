package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/02/2025.
 * #3650 https://leetcode.com/problems/minimum-cost-path-with-edge-reversals/
 */
public class MinimumCostPathWithEdgeReversals {

    // time O(n + m * log(m)), space O(n + m)
    public int minCost(int n, int[][] edges) {
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) { // O(n)
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) { // O(m)
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2] * 2});
        }
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE); // O(n)
        cost[0] = 0;
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minQueue.offer(new int[]{0, 0});
        while (!minQueue.isEmpty()) { // O(m * log(m))
            int[] curr = minQueue.poll();
            int currentCost = curr[0], node = curr[1];
            if (currentCost > cost[node]) {
                continue;
            }
            if (node == n - 1) {
                return currentCost;
            }
            for (int[] next : adj[curr[1]]) {
                int nextNode = next[0], costToNext = next[1];
                int newCost = currentCost + costToNext;
                if (newCost < cost[nextNode]) {
                    cost[nextNode] = newCost;
                    minQueue.offer(new int[]{newCost, nextNode});
                }
            }
        }
        return -1;
    }
}
