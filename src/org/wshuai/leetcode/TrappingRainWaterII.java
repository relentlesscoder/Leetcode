package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 10/8/16.
 * #407 https://leetcode.com/problems/trapping-rain-water-ii/
 */
public class TrappingRainWaterII {
	//BFS: http://www.cnblogs.com/grandyang/p/5928987.html
	public int trapRainWater(int[][] heightMap) {
		if(heightMap == null || heightMap.length == 0 || heightMap[0].length == 0){
			return 0;
		}
		int[][] dir = new int[][]{
			{1, -1, 0, 0},
			{0, 0, 1, -1}
		};
		int R = heightMap.length;
		int C = heightMap[0].length;
		boolean[][] visited = new boolean[R][C];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				if(i == 0 || j == 0 || i == R - 1 || j == C - 1){
					pq.offer(new int[]{i, j, heightMap[i][j]});
					visited[i][j] = true;
				}
			}
		}
		int res = 0;
		while(!pq.isEmpty()){
			int[] cur = pq.poll();
			for(int k = 0; k < 4; k++){
				int i = cur[0] + dir[0][k];
				int j = cur[1] + dir[1][k];
				if(i >= 0 && j >= 0 && i < R && j < C && !visited[i][j]){
					res += Math.max(0, cur[2] - heightMap[i][j]);
					pq.offer(new int[]{i, j, Math.max(heightMap[i][j], cur[2])});
					visited[i][j] = true;
				}
			}
		}

		return res;
	}
}
