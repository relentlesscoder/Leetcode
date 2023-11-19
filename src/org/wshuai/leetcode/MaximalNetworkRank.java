package org.wshuai.leetcode;

/**
 * Created by Wei on 10/13/2020.
 * #1615 https://leetcode.com/problems/maximal-network-rank/
 */
public class MaximalNetworkRank {

	// time O(n^2), space O(n^2)
	public int maximalNetworkRank(int n, int[][] roads) {
		boolean[][] connected = new boolean[n][n];
		int[] degree = new int[n];
		for(int[] road : roads){
			degree[road[0]]++;
			degree[road[1]]++;
			connected[road[0]][road[1]] = true;
			connected[road[1]][road[0]] = true;
		}
		int res = 0;
		for(int i = 0; i < n; i++){
			for(int j = i + 1; j < n; j++){
				res = Math.max(res, degree[i] + degree[j]
					+ (connected[i][j] ? -1 : 0));
			}
		}
		return res;
	}
}
