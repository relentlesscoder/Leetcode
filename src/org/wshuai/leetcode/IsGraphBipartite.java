package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 09/24/2019.
 * #0785 https://leetcode.com/problems/is-graph-bipartite/
 */
public class IsGraphBipartite {

	// time O(n), space O(n)
	public boolean isBipartite(int[][] graph) {
		int n = graph.length;
		int[] colors = new int[n];
		for(int i = 0; i < n; i++){
			if(colors[i] == 0 && !dfs(i, 1, colors, graph)){
				return false;
			}
		}
		return true;
	}

	private boolean dfs(int cur, int color, int[] colors, int[][] graph){
		if(colors[cur] != 0){
			return colors[cur] == color;
		}
		colors[cur] = color;
		for(int next : graph[cur]){
			if(!dfs(next, -color, colors, graph)){
				return false;
			}
		}
		return true;
	}

	// time O(n), space O(n)
	public boolean isBipartiteBFS(int[][] graph) {
		int n = graph.length;
		int[] colors = new int[n];
		for(int i = 0; i < n; i++){
			if(colors[i] != 0){
				continue;
			}
			LinkedList<Integer> queue = new LinkedList<>();
			queue.offerLast(i);
			colors[i] = 1;
			while(!queue.isEmpty()){
				int cur = queue.pollFirst();
				for(int next : graph[cur]){
					if(colors[next] == 0){
						colors[next] = -colors[cur];
						queue.offerLast(next);
					}else if(colors[next] != -colors[cur]){
						return false;
					}
				}
			}
		}
		return true;
	}
}
