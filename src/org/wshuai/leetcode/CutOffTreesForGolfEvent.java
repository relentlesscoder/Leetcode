package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/24/19.
 * #675 https://leetcode.com/problems/cut-off-trees-for-golf-event/
 */
public class CutOffTreesForGolfEvent {
	public int cutOffTree(List<List<Integer>> forest) {
		int[][] dirs = new int[][]{
			{1, -1, 0, 0},
			{0, 0, 1, -1}
		};
		int R = forest.size();
		if(R == 0){
			return 0;
		}
		int C = forest.get(0).size();
		if(C == 0){
			return 0;
		}
		int[][] grid = new int[R][C];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				int val = forest.get(i).get(j);
				grid[i][j] = val;
				if(val <= 1){
					continue;
				}
				pq.offer(new int[]{val, i, j});
			}
		}
		if(pq.size() == 0){
			return 0;
		}
		int step = 0;
		LinkedList<int[]> queue = new LinkedList<>();
		Set<Long> visited = new HashSet<>();
		queue.offerLast(new int[]{0, 0});
		visited.add(0L);
		int[] target = new int[]{pq.peek()[1], pq.peek()[2]};
		pq.poll();
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				if(cur[0] == target[0] && cur[1] == target[1]){
					if(pq.isEmpty()){
						return step;
					}
					target = new int[]{pq.peek()[1], pq.peek()[2]};
					pq.poll();
					queue.clear();
					queue.offerLast(new int[]{cur[0], cur[1]});
					visited.clear();
					visited.add(50L * cur[0] + cur[1]);
					step--;
					break;
				}
				for(int k = 0; k < 4; k++){
					int x = cur[0] + dirs[0][k];
					int y = cur[1] + dirs[1][k];
					if(x >= 0 && x < R && y >= 0 && y < C && grid[x][y] >= 1){
						if(visited.add(50L * x + y)){
							queue.offerLast(new int[]{x, y});
						}
					}
				}
			}
			step++;
		}
		return -1;
	}
}
