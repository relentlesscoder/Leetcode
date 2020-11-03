package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/26/2019.
 * #1197 https://leetcode.com/problems/minimum-knight-moves/
 */
public class MinimumKnightMoves {

	// time O(m*n), space O(m*n)
	// symmetric https://leetcode.com/problems/minimum-knight-moves/discuss/392053/Here-is-how-I-get-the-formula-(with-graphs)
	public int minKnightMoves(int x, int y) {
		x = Math.abs(x);
		y = Math.abs(y);
		Map<Integer, Integer> dp = new HashMap<>();
		return dfs(x, y, dp);
	}

	private int dfs(int x, int y, Map<Integer, Integer> dp){
		int key = x * 1_000 + y;
		if(dp.containsKey(key)){
			return dp.get(key);
		}
		if(x + y == 0){
			return 0;
		}else if(x + y == 2){
			return 2;
		}
		int min = Math.min(dfs(Math.abs(x - 1), Math.abs(y - 2), dp),
				dfs(Math.abs(x - 2), Math.abs(y - 1), dp)) + 1;
		dp.put(key, min);
		return min;
	}

	private static final int[][] DIRECTIONS = new int[][]{
			{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}
	};

	// time O(m*n), space O(m*n)
	public int minKnightMovesBFS(int x, int y) {
		x = Math.abs(x);
		y = Math.abs(y);
		int steps = 0;
		LinkedList<int[]> queue = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		queue.offerLast(new int[]{0, 0});
		visited.add(0);
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				if(cur[0] == x && cur[1] == y){
					return steps;
				}
				for(int i = 0; i < 8; i++){
					int x1 = cur[0] + DIRECTIONS[i][0], y1 = cur[1] + DIRECTIONS[i][1], key = x1 * 601 + y1;
					// special case
					// for example, to reach (1,1) from (0, 0), the best way is to get (2, -1) or (-1, 2) first,
					// then (1,1) (two steps). If we eliminate all coordinates with negative numbers,
					// then we can't reach (1,1) from (0, 0) within two steps.
					if(!visited.contains(key) && x1 >= -1 && y1 >= -1){
						visited.add(key);
						queue.offerLast(new int[]{x1, y1});
					}
				}
			}
			steps++;
		}
		return -1;
	}

	/** Non-optimized
	public int minKnightMoves(int x, int y) {
		if(x == 0 && y == 0){
			return 0;
		}
		int steps = 0;
		Set<Integer> visited = new HashSet<>();
		LinkedList<int[]> queue = new LinkedList<>();
		queue.offerLast(new int[]{0, 0});
		visited.add(0);
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				if(cur[0] == x && cur[1] == y){
					return steps;
				}
				for(int i = 0; i < 8; i++){
					int x1 = cur[0] + DIRECTIONS[i][0], y1 = cur[1] + DIRECTIONS[i][1], key = x1 * 601 + y1;
					if(!visited.contains(key)){
						visited.add(key);
						queue.offerLast(new int[]{x1, y1});
					}
				}
			}
			steps++;
		}
		return -1;
	}**/
}
