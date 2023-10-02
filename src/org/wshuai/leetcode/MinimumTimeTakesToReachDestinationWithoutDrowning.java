package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 10/01/2023.
 * #2814 https://leetcode.com/problems/minimum-time-takes-to-reach-destination-without-drowning/
 */
public class MinimumTimeTakesToReachDestinationWithoutDrowning {

	private int[] dirs = new int[]{0, -1, 0, 1, 0};

	// time O(n * m), space O(n * m)
	public int minimumSeconds(List<List<String>> land) {
		int n = land.size(), m = land.get(0).size();
		int[] target = new int[] {-1, -1};
		Deque<int[]> floodQueue = new ArrayDeque<>(), pathQueue = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				String val = land.get(i).get(j);
				if (val.equals("*")) {
					floodQueue.offer(new int[]{i, j});
				} else if (val.equals("S")) {
					pathQueue.offer(new int[]{i, j, 0});
				} else if (val.equals("D")) {
					target[0] = i;
					target[1] = j;
				}
			}
		}
		boolean[][] visited = new boolean[n][m];
		while (!pathQueue.isEmpty()) {
			int floodSize = floodQueue.size();
			while (floodSize-- > 0) {
				int[] curr = floodQueue.poll();
				for (int k = 0; k < 4; k++) {
					int x = curr[0] + dirs[k], y = curr[1] + dirs[k + 1];
					if (x < 0 || x >= n || y < 0 || y >= m || land.get(x).get(y).equals("X") || land.get(x).get(y).equals("D") || land.get(x).get(y).equals("*")) {
						continue;
					}
					land.get(x).set(y, "*");
					floodQueue.offer(new int[] {x, y});
				}
			}
			int pathSize = pathQueue.size();
			while (pathSize-- > 0) {
				int[] curr = pathQueue.poll();
				if (curr[0] == target[0] && curr[1] == target[1]) {
					return curr[2];
				}
				if (visited[curr[0]][curr[1]]) {
					continue;
				}
				visited[curr[0]][curr[1]] = true;
				for (int k = 0; k < 4; k++) {
					int x = curr[0] + dirs[k], y = curr[1] + dirs[k + 1];
					if (x < 0 || x >= n || y < 0 || y >= m || land.get(x).get(y).equals("X") || land.get(x).get(y).equals("*") || visited[x][y]) {
						continue;
					}
					pathQueue.offer(new int[] {x, y, curr[2] + 1});
				}
			}
		}
		return -1;
	}
}
