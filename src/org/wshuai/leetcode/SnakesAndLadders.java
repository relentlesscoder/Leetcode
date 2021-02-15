package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Wei on 09/21/2019.
 * #0909 https://leetcode.com/problems/snakes-and-ladders/
 */
public class SnakesAndLadders {

	// time O(n*n), space O(n*n)
	public int snakesAndLadders(int[][] board) {
		int n = board.length, target = n * n;
		int[] dist = new int[target + 1]; // record min distance to cell
		Arrays.fill(dist, -1);
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offerLast(1);
		dist[1] = 0;
		while(!queue.isEmpty()){
			int cur = queue.pollFirst();
			if(cur == target){
				return dist[cur];
			}
			for(int next = cur + 1; next <= Math.min(cur + 6, target); next++){
				int[] cell = getIndex(next, n);
				int nextIndex = board[cell[0]][cell[1]] != -1 ?
					board[cell[0]][cell[1]] : next;
				if(dist[nextIndex] == -1){
					dist[nextIndex] = dist[cur] + 1;
					queue.offerLast(nextIndex);
				}
			}
		}
		return -1;
	}

	private int[] getIndex(int index, int n){
		int row = n - 1 - ((index - 1) / n);
		int col = (index - 1) % n;
		col = (row % 2 == n % 2) ? n - 1 - col : col;
		return new int[]{ row, col };
	}
}
