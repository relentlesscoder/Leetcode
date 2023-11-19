package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 05/06/2021.
 * #1661 https://leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/
 */
public class MinimumDegreeOfAConnectedTrioInAGraph {

    // time O(n^3), space O(n^2)
    public int minTrioDegree(int n, int[][] edges) {
        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> degree = new HashMap<>();
        boolean[][] adj = new boolean[n + 1][n + 1];
        for(int[] edge : edges){
            adj[edge[0]][edge[1]] = true;
            adj[edge[1]][edge[0]] = true;
            degree.put(edge[0], degree.getOrDefault(edge[0], 0) + 1);
            degree.put(edge[1], degree.getOrDefault(edge[1], 0) + 1);
        }
        for(int[] edge : edges){
            for(int i = 1; i <= n; i++){
                if(adj[edge[0]][i] && adj[edge[1]][i]){ // for each edge, if both nodes connect to the same node then they form a trio
                    int degreeOfTrio = degree.get(edge[0]) + degree.get(edge[1]) + degree.get(i) - 6;
                    min = Math.min(min, degreeOfTrio);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
