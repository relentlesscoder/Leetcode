package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/30/2023.
 * #1992 https://leetcode.com/problems/find-all-groups-of-farmland/
 */
public class FindAllGroupsOfFarmland {

	private int[] dirs = new int[]{0, -1, 0, 1, 0};

	// time O(m * n), space O(m + n)
	public int[][] findFarmland(int[][] land) {
		List<int[]> list = new ArrayList<>();
		int m = land.length, n = land[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (land[i][j] == 1) {
					int[] bottomRight = new int[]{-1, -1};
					dfs(land, m, n, i, j, bottomRight);
					list.add(new int[]{i, j, bottomRight[0], bottomRight[1]});
				}
			}
		}
		int[][] res = new int[list.size()][4];
		for (int i = 0; i < list.size(); i++) {
			res[i] = list.get(i);
		}
		return res;
	}

	private void dfs(int[][] land, int m, int n, int i, int j, int[] target) {
		land[i][j] = 0;
		target[0] = Math.max(target[0], i);
		target[1] = Math.max(target[1], j);
		for (int k = 0; k < 4; k++) {
			int x = i + dirs[k], y = j + dirs[k + 1];
			if (x >= 0 && x < m && y >= 0 && y < n && land[x][y] == 1) {
				dfs(land, m, n, x, y, target);
			}
		}
	}
}
