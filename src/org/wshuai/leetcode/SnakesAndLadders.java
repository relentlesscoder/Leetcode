package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Wei on 09/21/2019.
 * #0909 https://leetcode.com/problems/snakes-and-ladders/
 */
public class SnakesAndLadders {

	private int[][] board;
	private int N;

	public int snakesAndLadders(int[][] board) {
		this.board = board;
		N = board.length;
		int target = N * N;

		Map<Integer, Integer> dist = new HashMap();
		dist.put(1, 0); // position, distance

		LinkedList<Integer> queue = new LinkedList<>();
		queue.offerLast(1);

		while (!queue.isEmpty()) {
			int cur = queue.pollFirst();
			if (cur == target) {
				return dist.get(cur);
			}
			for (int next = cur + 1; next <= Math.min(cur + 6, target); next++) {
				int[] index = getIndex(next);
				int nextIndex = board[index[0]][index[1]] == -1 ?
					next : board[index[0]][index[1]]; // check if it is a ladder
				if (!dist.containsKey(nextIndex)) {
					dist.put(nextIndex, dist.get(cur) + 1);
					queue.offerLast(nextIndex);
				}
			}
		}
		return -1;
	}

	private int[] getIndex(int num) {
		int r = N - 1 - ((num - 1) / N);
		int j = (num - 1) % N;
		int c = r % 2 != N % 2 ? j : N - 1 - j; // odd and even row is different direction
		return new int[]{r, c};
	}
}
