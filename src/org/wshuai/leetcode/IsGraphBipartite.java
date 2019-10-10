package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 9/24/19.
 * #785 https://leetcode.com/problems/is-graph-bipartite/
 */
public class IsGraphBipartite {
	public boolean isBipartiteDFS(int[][] graph) {
		int[] colors = new int[graph.length];

		for(int i = 0; i < graph.length; i++){
			if(colors[i] == 0 && !validColor(graph, colors, 1, i)){
				return false;
			}
		}

		return true;
	}

	private boolean validColor(int[][] graph, int[] colors, int color, int node){
		if(colors[node] != 0){
			return colors[node] == color;
		}
		colors[node] = color;
		for(int next: graph[node]){
			if(!validColor(graph, colors, -color, next)){
				return false;
			}
		}
		return true;
	}

	public boolean isBipartiteBFS(int[][] graph) {
		int[] colors = new int[graph.length];

		for(int i = 0; i < graph.length; i++){
			if(colors[i] != 0){
				continue;
			}
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(i);
			colors[i] = 1;

			while(!queue.isEmpty()){
				int curr = queue.poll();
				for(int v: graph[curr]){
					if(colors[v] == 0){
						colors[v] = -colors[curr];
						queue.offer(v);
					}else if(colors[v] != -colors[curr]){
						return false;
					}
				}
			}
		}

		return true;
	}
}
