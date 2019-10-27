package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/26/19.
 * #1162 https://leetcode.com/problems/as-far-from-land-as-possible/
 */
public class AsFarFromLandAsPossible {
	public int maxDistance(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		boolean[][] visited = new boolean[m][n];
		LinkedList<int[]> queue = new LinkedList<>();
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 1){
					visited[i][j] = true;
					queue.offerLast(new int[]{i, j});
				}
			}
		}

		int[][] dir = new int[][]{
			{1, -1, 0, 0}, {0, 0, 1, -1}
		};
		int result = -1;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] curr = queue.pollFirst();
				result = Math.max(result, grid[curr[0]][curr[1]] - 1);
				for(int i = 0; i < 4; i++){
					int x = curr[0] + dir[0][i];
					int y = curr[1] + dir[1][i];
					if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
						visited[x][y] = true;
						grid[x][y] = grid[curr[0]][curr[1]] + 1;
						queue.offer(new int[]{x, y});
					}
				}
			}
		}
		return result == 0 ? -1 : result;
	}
}
