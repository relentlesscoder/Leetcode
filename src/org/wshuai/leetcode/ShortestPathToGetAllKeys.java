package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 11/23/19.
 * #864 https://leetcode.com/problems/shortest-path-to-get-all-keys/
 */
public class ShortestPathToGetAllKeys {
	public int shortestPathAllKeys(String[] grid) {
		int[][] dirs = new int[][]{
			{1, -1, 0, 0}, {0, 0, 1, -1}
		};
		int R = grid.length;
		int C = grid[0].length();
		LinkedList<int[]> queue = new LinkedList<>();
		int m = -1;
		int n = -1;
		int target = 0;
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				char c = grid[i].charAt(j);
				if(c == '@'){
					m = i;
					n = j;
				}
				if(c >= 'a' && c <= 'f'){
					target |= (1 << (c - 'a'));
				}
			}
		}
		queue.offerLast(new int[]{0, m, n});
		Set<String> visited = new HashSet<>();
		visited.add("0," + m + "," + n);
		int step = 0;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				int i = cur[1];
				int j = cur[2];
				for(int k = 0; k < 4; k++){
					int s = cur[0];
					int x = i + dirs[0][k];
					int y = j + dirs[1][k];
					if(x < 0 || y < 0 || x >= R || y >= C || grid[x].charAt(y) == '#'){
						continue;
					}
					char c = grid[x].charAt(y);
					if(c >= 'a' && c <= 'f'){
						s |= (1 << (c - 'a'));
						if(s == target){
							return step + 1;
						}
					}
					if(c >= 'A' && c <= 'F' && ((s >> (c - 'A')) & 1) == 0){
						continue;
					}
					if(visited.add(s + "," + x + "," + y)){
						queue.offerLast(new int[]{s, x, y});
					}
				}
			}
			step++;
		}
		return -1;
	}
}
