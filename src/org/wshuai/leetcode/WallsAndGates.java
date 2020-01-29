package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/01/2016.
 * #0286 https://leetcode.com/problems/walls-and-gates/
 */
public class WallsAndGates {
	private static final int[] dirs = new int[]{0, 1, 0, -1, 0};

	// time O(r*c), space O(n)
	public void wallsAndGates(int[][] rooms) {
		if(rooms == null || rooms.length == 0 || rooms[0].length == 0){
			return;
		}
		int r = rooms.length, c = rooms[0].length;
		LinkedList<int[]> queue = new LinkedList<>();
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if(rooms[i][j] == 0){
					queue.offerLast(new int[]{i, j});
				}
			}
		}

		int dist = 0;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				for(int k = 0; k < 4; k++){
					int x = cur[0] + dirs[k];
					int y = cur[1] + dirs[k + 1];
					if(x < 0 || x >= r || y < 0 || y >= c || rooms[x][y] != Integer.MAX_VALUE){
						continue;
					}
					rooms[x][y] = dist + 1;
					queue.offerLast(new int[]{x, y});
				}
			}
			dist++;
		}
	}
}
