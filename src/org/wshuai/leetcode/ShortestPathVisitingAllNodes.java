package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Wei on 11/22/19.
 * #847 https://leetcode.com/problems/shortest-path-visiting-all-nodes/
 */
public class ShortestPathVisitingAllNodes {

	// BFS + DP + Bitmask
	public int shortestPathLength(int[][] graph) {
		int N = graph.length;
		int[][] dp = new int[N][1 << N];
		LinkedList<int[]> queue = new LinkedList<>();
		for(int i = 0; i < N; i++){
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			dp[i][1 << i] = 0;
			queue.offerLast(new int[]{1 << i, i});
		}

		while(!queue.isEmpty()){
			int[] p = queue.pollFirst();

			for(int next : graph[p[1]]){
				int mask = p[0] | (1 << next);
				if(dp[next][mask] > dp[p[1]][p[0]] + 1){
					dp[next][mask] = dp[p[1]][p[0]] + 1;
					queue.offer(new int[]{mask, next});
				}
			}
		}

		int res = Integer.MAX_VALUE;
		for(int i = 0; i < graph.length; i++){
			res = Math.min(res, dp[i][(1 << N) - 1]);
		}
		return res;
	}
}
