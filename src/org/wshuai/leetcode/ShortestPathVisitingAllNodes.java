package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Wei on 11/22/2019.
 * #0847 https://leetcode.com/problems/shortest-path-visiting-all-nodes/
 */
public class ShortestPathVisitingAllNodes {

	// time O(2^n), space O(2^n)
	// BFS + DP + Bitmask
	public int shortestPathLength(int[][] graph) {
		int n = graph.length, target = (1 << n) - 1;
		LinkedList<int[]> queue = new LinkedList<>();
		// dp[i][j] denotes the visit status is j (bitmask)
		// when the current visiting node is i
		int[][] dp = new int[n][1 << n];
		for(int i = 0; i < n; i++){
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			dp[i][1 << i] = 0;
			// could start from any node
			queue.offerLast(new int[]{i, 1 << i});
		}
		while(!queue.isEmpty()){
			int[] cur = queue.pollFirst();
			for(int next : graph[cur[0]]){
				int mask = (1 << next) | cur[1];
				if(mask == target){
					return dp[cur[0]][cur[1]] + 1;
				}
				if(dp[next][mask] > dp[cur[0]][cur[1]] + 1){
					dp[next][mask] = dp[cur[0]][cur[1]] + 1;
					queue.offerLast(new int[]{next, mask});
				}
			}
		}
		return 0;
	}
}
