package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 11/22/19.
 * #1210 https://leetcode.com/problems/minimum-moves-to-reach-target-with-rotations/
 */
public class MinimumMovesToReachTargetWithRotations {
	public int minimumMoves(int[][] grid) {
		int N = grid.length;
		int[] start = new int[]{0, 1, 1};
		Set<String> visited = new HashSet<>();
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(start);
		visited.add("0,1,1");
		int step = 0;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] curr = queue.pollFirst();
				// horizontal
				int hx = curr[0];
				int hy = curr[1];
				int ho = curr[2];
				if(ho == 1){
					//move right
					if(hy < N - 1 && grid[hx][hy + 1] == 0){
						int[] next = new int[]{hx, hy + 1, 1};
						if(next[0] == N - 1 && next[1] == N - 1){
							return step + 1;
						}
						if(visited.add("" + next[0] + "," + next[1] + "," + next[2])){
							queue.offerLast(next);
						}
					}
					if(hx < N - 1 && grid[hx + 1][hy] == 0 && grid[hx + 1][hy - 1] == 0){
						//move down
						int[] next1 = new int[]{hx + 1, hy, 1};
						if(next1[0] == N - 1 && next1[1] == N - 1){
							return step + 1;
						}
						if(visited.add("" + next1[0] + "," + next1[1] + "," + next1[2])){
							queue.offerLast(next1);
						}
						//rotate clockwise
						int[] next2 = new int[]{hx + 1, hy - 1, 0};
						if(visited.add("" + next2[0] + "," + next2[1] + "," + next2[2])){
							queue.offerLast(next2);
						}
					}
				}else{
					//move down
					if(hx < N - 1 && grid[hx + 1][hy] == 0){
						int[] next = new int[]{hx + 1, hy, 0};
						if(visited.add("" + next[0] + "," + next[1] + "," + next[2])){
							queue.offerLast(next);
						}
					}
					if(hy < N - 1 && grid[hx][hy + 1] == 0 && grid[hx - 1][hy + 1] == 0){
						//move right
						int[] next1 = new int[]{hx, hy + 1, 0};
						if(visited.add("" + next1[0] + "," + next1[1] + "," + next1[2])){
							queue.offerLast(next1);
						}
						//rotate counter-clockwise
						int[] next2 = new int[]{hx - 1, hy + 1, 1};
						if(next2[0] == N - 1 && next2[1] == N - 1){
							return step + 1;
						}
						if(visited.add("" + next2[0] + "," + next2[1] + "," + next2[2])){
							queue.offerLast(next2);
						}
					}
				}
			}
			step++;
		}
		return -1;
	}
}
