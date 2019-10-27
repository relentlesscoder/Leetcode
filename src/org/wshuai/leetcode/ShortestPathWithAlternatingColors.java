package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 10/26/19.
 * #1129 https://leetcode.com/problems/shortest-path-with-alternating-colors/
 */
public class ShortestPathWithAlternatingColors {

	// good explanation https://leetcode.com/problems/shortest-path-with-alternating-colors/discuss/340258/Easy-understanding-Java-BFS-Solution-with-Video-Explanation
	public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
		int[][] g = new int[n][n];
		buildGraph(g, n, red_edges, blue_edges);

		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(new int[]{0, 1});
		queue.offerLast(new int[]{0, -1});
		int len = 0;
		int[] res = new int[n];
		Arrays.fill(res, Integer.MAX_VALUE);
		res[0] = 0;

		Set<String> visited = new HashSet<>();
		while (!queue.isEmpty()){
			int size = queue.size();
			len++;
			for(int i = 0; i < size; i++){
				int[] curr = queue.pollFirst();
				int node = curr[0];
				int color = curr[1];
				int oppoColor = -color;

				for(int j = 1; j < n; j++){
					if(g[node][j] == oppoColor || g[node][j] == 0){
						if(!visited.add(j + "," + oppoColor)){
							continue;
						}
						queue.offerLast(new int[]{j, oppoColor});
						res[j] = Math.min(res[j], len);
					}
				}
			}
		}

		for(int i = 0; i < n; i++){
			if(res[i] == Integer.MAX_VALUE){
				res[i] = -1;
			}
		}
		return res;
	}

	private void buildGraph(int[][] g, int n, int[][] red_edges, int[][] blue_edges){
		for(int i = 0; i < n; i++){
			Arrays.fill(g[i], -n);
		}

		for(int[] e : red_edges){
			g[e[0]][e[1]] = 1;
		}

		for(int[] e: blue_edges){
			int u = e[0];
			int v = e[1];
			if(g[u][v] == 1){
				g[u][v] = 0;
			}else{
				g[u][v] = -1;
			}
		}
	}
}
