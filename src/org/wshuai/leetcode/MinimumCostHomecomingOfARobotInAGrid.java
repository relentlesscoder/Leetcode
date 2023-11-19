package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/21/2023.
 * #2087 https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/
 */
public class MinimumCostHomecomingOfARobotInAGrid {

	// time O(m + n), space O(1)
	public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
		int res = 0, startRow = startPos[0], endRow = homePos[0], startCol = startPos[1], endCol = homePos[1];
		boolean down = startPos[0] < homePos[0], right = startPos[1] < homePos[1];
		// no matter how we get to home, the optimal cost is the same
		while (startRow != endRow) {
			startRow += down ? 1 : -1;
			res += rowCosts[startRow];
		}
		while (startCol != endCol) {
			startCol += right ? 1 : -1;
			res += colCosts[startCol];
		}
		return res;
	}

	private static final int[] DIRS = new int[] {-1, 1};

	// time O(m * n * log(m * n)), space O(m * n)
	public int minCostBFS(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
		int m = rowCosts.length, n = colCosts.length;
		boolean down = startPos[0] < homePos[0], right = startPos[1] < homePos[1];
		int[][] visited = new int[m][n];
		PriorityQueue<int[]> minQueue = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
		minQueue.offer(new int[] {0, startPos[0], startPos[1]});
		visited[startPos[0]][startPos[1]] = 1;
		while (!minQueue.isEmpty()) {
			int[] curr = minQueue.poll();
			if (curr[1] == homePos[0] && curr[2] == homePos[1]) {
				return curr[0];
			}
			int x1 = curr[1], y1 = curr[2] + (right ? 1 : -1);
			if (y1 >= 0 && y1 < n && visited[x1][y1] == 0) {
				visited[x1][y1] = 1;
				minQueue.offer(new int[] {curr[0] + colCosts[y1], x1, y1});
			}
			int x2 = curr[1] + (down ? 1 : -1), y2 = curr[2];
			if (x2 >= 0 && x2 < m && visited[x2][y2] == 0) {
				visited[x2][y2] = 1;
				minQueue.offer(new int[] {curr[0] + rowCosts[x2], x2, y2});
			}
		}
		return -1;
	}
}
