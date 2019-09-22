package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Wei on 9/21/19.
 * #909 https://leetcode.com/problems/snakes-and-ladders/
 */
public class SnakesAndLadders {
	private int[][] board;
	private int N;

	public int snakesAndLadders(int[][] board) {
		this.board = board;
		N = board.length;

		Map<Integer, Integer> dist = new HashMap();
		dist.put(1, 0);

		LinkedList<Integer> queue = new LinkedList<>();
		queue.offerLast(1);

		while(!queue.isEmpty()){
			int s = queue.pollFirst();
			if(s == N*N){
				return dist.get(s);
			}

			for(int s2 = s+1; s2 <= Math.min(s+6, N*N); s2++){
				int[] index = getIndex(s2);
				int s2Final = board[index[0]][index[1]] == -1 ? s2 : board[index[0]][index[1]];
				if(!dist.containsKey(s2Final)){
					dist.put(s2Final, dist.get(s) + 1);
					queue.offerLast(s2Final);
				}
			}
		}
		return -1;
	}

	private int[] getIndex(int num){
		int r = N - 1 - ((num - 1) / N);
		int j = (num - 1) % N;
		int c = r % 2 != N % 2 ? j : N - 1 - j;
		return new int[]{r, c};
	}
}
