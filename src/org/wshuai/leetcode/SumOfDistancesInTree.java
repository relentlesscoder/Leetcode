package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/18/2019.
 * #834 https://leetcode.com/problems/sum-of-distances-in-tree/
 */
public class SumOfDistancesInTree {
	private int[] res;
	private int[] subtreeNodes;
	private List<Integer>[] adj;
	private int N;

	// https://leetcode.com/problems/sum-of-distances-in-tree/discuss/130567/Two-traversals-O(N)-python-solution-with-Explanation
	public int[] sumOfDistancesInTree(int N, int[][] edges) {
		this.N = N;
		res = new int[N];
		subtreeNodes = new int[N];

		adj = new ArrayList[N];
		for(int i = 0; i < N; i++){
			adj[i] = new ArrayList<>();
		}
		for(int[] e : edges){
			adj[e[0]].add(e[1]);
			adj[e[1]].add(e[0]);
		}
		dfs(-1, 0);
		distanceSum(0, 0);

		return res;
	}

	private int[] dfs(int prev, int cur){
		int num = 0;
		int sum = 0;
		for(int i : adj[cur]){
			if(i == prev){
				continue;
			}
			int[] countSum = dfs(cur, i);
			sum += countSum[0] + countSum[1];
			num += countSum[0];
		}
		subtreeNodes[cur] = num + 1;
		res[cur] = sum;
		return new int[]{subtreeNodes[cur], res[cur]};
	}

	private void distanceSum(int prev, int cur){
		if(cur != 0){
			res[cur] = res[prev] - 2 * subtreeNodes[cur] + N;
		}
		for(int i : adj[cur]){
			if(i == prev){
				continue;
			}
			distanceSum(cur, i);
		}
	}
}
