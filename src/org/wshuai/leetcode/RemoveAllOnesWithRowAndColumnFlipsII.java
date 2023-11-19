package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2023.
 * #2174 https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips-ii/
 */
public class RemoveAllOnesWithRowAndColumnFlipsII {

	// time O((m * n) ^ min(m, n)), space O(m * min(m, n))
	public int removeOnes(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[] state = new int[m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					state[i] |= (1 << j); // use bit map to represent row state
				}
			}
		}
		return remove(state, m, n);
	}

	private int remove(int[] state, int m, int n) {
		int cost = Integer.MAX_VALUE;
		boolean done = true;
		for (int i = 0; i < m; i++) {
			if (state[i] == 0) {
				continue;
			}
			done = false;
			for (int j = 0; j < n; j++) {
				if (((state[i] >> j) & 1) == 1) {
					int[] nextState = state.clone();
					nextState[i] = 0;
					for (int k = 0; k < m; k++) {
						if (((nextState[k] >> j) & 1) == 1) {
							nextState[k] -= (1 << j);
						}
					}
					cost = Math.min(cost, 1 + remove(nextState, m, n));
				}
			}
		}
		return done ? 0 : cost;
	}
}
