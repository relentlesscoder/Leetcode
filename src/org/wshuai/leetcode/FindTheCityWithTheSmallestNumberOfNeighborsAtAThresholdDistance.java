package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/27/2020.
 * #1334 https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
 */
public class FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {
	// time O(V^3), space O(V^2)
	// https://www.techiedelight.com/pairs-shortest-paths-floyd-warshall-algorithm/
	public int findTheCity(int n, int[][] edges, int distanceThreshold) {
		int[][] cost = new int[n][n];

		for(int i = 0; i < n; i++){
			Arrays.fill(cost[i], Integer.MAX_VALUE);
			cost[i][i] = 0;
		}
		for(int[] e : edges){
			cost[e[0]][e[1]] = e[2];
			cost[e[1]][e[0]] = e[2];
		}
		for(int k = 0; k < n; k++){
			for(int u = 0; u < n; u++){
				for(int v = 0; v < n; v++){
					if(cost[u][k] != Integer.MAX_VALUE
							&& cost[k][v] != Integer.MAX_VALUE
							&& cost[u][k] + cost[k][v] < cost[u][v]){
						cost[u][v] = cost[u][k] + cost[k][v];
					}
				}
			}
		}
		int min = n, res = 0;
		for(int i = 0; i < n; i++){
			int count = 0;
			for(int j = 0; j < n; j++){
				if(cost[i][j] <= distanceThreshold){
					count++;
				}
			}
			if(count <= min){
				min = count;
				res = i;
			}
		}
		return res;
	}
}
