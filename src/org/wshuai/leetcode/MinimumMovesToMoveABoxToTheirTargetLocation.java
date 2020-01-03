package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 12/9/2019.
 * #1263 https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/
 */
public class MinimumMovesToMoveABoxToTheirTargetLocation {
	private int[][] dirs;
	private int m;
	private int n;

	// https://www.youtube.com/watch?v=LtJgcasp5J8 (BFS + BFS)
	public int minPushBox(char[][] grid) {
		dirs = new int[][]{
			{1, -1, 0, 0},
			{0, 0, 1, -1}
		};
		m = grid.length;
		n = grid[0].length;

		GameStatus start = new GameStatus();
		GameStatus end = new GameStatus();
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == 'S'){
					start.px = i;
					start.py = j;
				}
				if(grid[i][j] == 'T'){
					end.bx = i;
					end.by = j;
				}
				if(grid[i][j] == 'B'){
					start.bx = i;
					start.by = j;
				}
			}
		}

		int steps = 0;
		Set<Integer> visited = new HashSet<>();
		LinkedList<GameStatus> queue = new LinkedList<>();
		queue.offerLast(start);
		visited.add(start.getStatus());
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				GameStatus cur = queue.pollFirst();
				for(int k = 0; k < 4; k++){
					GameStatus next = new GameStatus();
					if(!canMove(cur, k, next, grid) || visited.contains(next.getStatus())){
						continue;
					}
					if(next.bx == end.bx && next.by == end.by){
						return steps + 1;
					}
					visited.add(next.getStatus());
					queue.offerLast(next);
				}
			}
			steps++;
		}
		return -1;
	}

	private boolean canMove(GameStatus cur, int k, GameStatus next, char[][] grid){
		int bx = cur.bx + dirs[0][k];
		int by = cur.by + dirs[1][k];
		if(bx < 0 || by < 0 || bx >= m || by >= n || grid[bx][by] == '#'){
			return false;
		}
		if(!canReach(cur, cur.bx - dirs[0][k], cur.by - dirs[1][k], grid)){
			return false;
		}
		next.bx = bx;
		next.by = by;
		next.px = cur.bx;
		next.py = cur.by;
		return true;
	}

	private boolean canReach(GameStatus cur, int tx, int ty, char[][] grid){
		boolean[] visited = new boolean[m * n];
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(new int[]{cur.px, cur.py});
		visited[cur.px * n + cur.py] = true;
		while(!queue.isEmpty()){
			int[] node = queue.pollFirst();
			for(int i = 0; i < 4; i++){
				int x = node[0] + dirs[0][i];
				int y = node[1] + dirs[1][i];
				if(x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == '#'){
					continue;
				}
				if(cur.bx == x && cur.by == y){
					continue;
				}
				if(tx == x && ty == y){
					return true;
				}
				if(!visited[x * n + y]){
					queue.offerLast(new int[]{x, y});
					visited[x * n + y] = true;
				}
			}
		}
		return false;
	}
}

class GameStatus{
	int px;
	int py;
	int bx;
	int by;
	int getStatus(){
		return ((bx * 20 + by) << 16) | (px * 20 + py);
	}
}