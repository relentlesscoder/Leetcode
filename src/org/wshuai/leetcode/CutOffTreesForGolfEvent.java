package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/24/2019.
 * #0675 https://leetcode.com/problems/cut-off-trees-for-golf-event/
 */
public class CutOffTreesForGolfEvent {
	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O(m^2 * n^2), space O(m^2 * n^2)
	public int cutOffTree(List<List<Integer>> forest) {
		int res = 0, m = forest.size(), n = forest.get(0).size();
		int[][] grid = new int[m][n];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		for(int i = 0; i < m; i++){
			List<Integer> list = forest.get(i);
			for(int j = 0; j < n; j++){
				grid[i][j] = list.get(j);
				if(grid[i][j] > 1){
					pq.offer(new int[]{i, j, grid[i][j]});
				}
			}
		}
		int[] cur = new int[]{0, 0};
		while(!pq.isEmpty()){
			int[] next = pq.poll();
			int steps = bfs(cur[0], cur[1], next, grid, m, n);
			if(steps == -1){
				return -1;
			}
			cur[0] = next[0];
			cur[1] = next[1];
			res += steps;
		}
		return res;
	}

	private int bfs(int i, int j, int[] target, int[][] grid, int m, int n){
		int steps = 0;
		boolean[] visited = new boolean[m * n];
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(new int[]{i, j});
		visited[i * n + j] = true;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				if(cur[0] == target[0] && cur[1] == target[1]){
					return steps;
				}
				for(int k = 0; k < 4; k++){
					int x = cur[0] + dirs[k], y = cur[1] + dirs[k + 1], index = x * n + y;
					if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0 || visited[index]){
						continue;
					}
					visited[index] = true;
					queue.offerLast(new int[]{x, y});
				}
			}
			steps++;
		}
		return -1;
	}
}
