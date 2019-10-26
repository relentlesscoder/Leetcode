package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 10/26/19.
 * #1197 https://leetcode.com/problems/minimum-knight-moves/
 */
public class MinimumKnightMoves {
	public int minKnightMoves(int x, int y) {
		if(x == 0 && y == 0){
			return 0;
		}
		int[][] move = new int[][]{
			{-1, -2, -2, -1, 1, 2, 2, 1},
			{-2, -1, 1, 2, 2, 1, -1, -2}
		};
		int res = 0;
		Set<Integer> visited = new HashSet<>();
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(new int[]{0, 0, 0});
		visited.add(0);
		while(!queue.isEmpty()){
			int[] p = queue.pollFirst();
			for(int i = 0; i < 8; i++){
				int m = p[0] + move[0][i];
				int n = p[1] + move[1][i];
				int dist = p[2] + 1;
				if(m == x && n == y){
					return dist;
				}
				int key = m * 1001 + n;
				if(!visited.contains(key) && (Math.abs(m) + Math.abs(n) <= 300)){
					queue.offerLast(new int[]{m, n, dist});
					visited.add(key);
				}
			}
		}
		return -1;
	}
}
