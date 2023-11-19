package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/01/2020.
 * #1548 https://leetcode.com/problems/the-most-similar-path-in-a-graph/
 */
public class TheMostSimilarPathInAGraph {

	private List<List<Integer>> adj;
	private int[][] minDistance;
	private int[][] minPath;
	private String[] names;
	private String[] target;

	// time O(n^2*m), space O(n*m)
	public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
		this.names = names;
		this.target = targetPath;
		int m = targetPath.length;
		minDistance = new int[n][m];
		minPath = new int[n][m];
		adj = new ArrayList<>();
		for(int i = 0; i < n; i++){
			adj.add(new ArrayList<Integer>());
		}
		for(int[] road : roads){
			adj.get(road[0]).add(road[1]);
			adj.get(road[1]).add(road[0]);
		}
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				minDistance[i][j] = -1;
			}
		}
		int min = Integer.MAX_VALUE, start = -1;
		for(int i = 0; i < n; i++){
			int cur = dfs(i, 0);
			if(cur < min){
				min = cur;
				start = i;
			}
		}
		List<Integer> res = new ArrayList<>();
		while(res.size() < m){
			res.add(start);
			start = minPath[start][res.size() - 1];
		}
		return res;
	}

	private int dfs(int cur, int index){
		if(minDistance[cur][index] != -1){
			return minDistance[cur][index];
		}
		int editDistance = names[cur].equals(target[index]) ? 0 : 1;
		if(index == target.length - 1){
			return editDistance;
		}
		int min = Integer.MAX_VALUE;
		for(int next : adj.get(cur)){
			int cost = dfs(next, index + 1);
			if(cost < min){
				min = cost;
				minPath[cur][index] = next;
			}
		}
		editDistance += min;
		minDistance[cur][index] = editDistance;
		return editDistance;
	}
}
