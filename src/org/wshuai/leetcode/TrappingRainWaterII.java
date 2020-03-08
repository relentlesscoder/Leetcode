package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 10/08/2016.
 * #0407 https://leetcode.com/problems/trapping-rain-water-ii/
 */
public class TrappingRainWaterII {
	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};
	// time O(m*n), space O(m*n)
	// BFS: http://www.cnblogs.com/grandyang/p/5928987.html
	public int trapRainWater(int[][] heightMap) {
		if(heightMap == null || heightMap.length == 0 || heightMap[0].length == 0){
			return 0;
		}
		int res = 0, m = heightMap.length, n = heightMap[0].length;
		boolean[][] visited = new boolean[m][n];
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(i == 0 || i == m - 1 || j == 0 || j == n - 1){
					queue.offer(new int[]{i, j, heightMap[i][j]});
					visited[i][j] = true;
				}
			}
		}
		while(!queue.isEmpty()){
			int[] cur = queue.poll();
			for(int i = 0; i < 4; i++){
				int x = cur[0] + dirs[i];
				int y = cur[1] + dirs[i + 1];
				if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
					res += Math.max(0, cur[2] - heightMap[x][y]);
					queue.offer(new int[]{x, y, Math.max(cur[2], heightMap[x][y])});
					visited[x][y] = true;
				}
			}
		}
		return res;
	}
}
