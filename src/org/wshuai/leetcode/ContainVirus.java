package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 12/24/2019.
 * #0749 https://leetcode.com/problems/contain-virus/
 */
public class ContainVirus {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};
    private record Group(int walls, Set<Integer> next, List<int[]> viruses) { }

	// time O(g * m * n), space O(m * n)
	public int containVirus(int[][] isInfected) {
		int walls = 0, m = isInfected.length, n = isInfected[0].length;
		List<Group> groups = buildGroups(isInfected);
		while (!groups.isEmpty()) {
			walls += groups.get(0).walls;
			// 对第一个病毒组(待感染的格子最多)加装防火墙
			for (int[] v : groups.get(0).viruses) {
				isInfected[v[0]][v[1]] = -1;
			}
			// 剩下的病毒组扩张一步
			for (int i = 1; i < groups.size(); i++) {
				for (int[] v : groups.get(i).viruses) {
					int r = v[0], c = v[1];
					for (int d = 0; d < 4; d++) {
						int x = r + DIRS[d], y = c + DIRS[d + 1];
						if (x >= 0 && x < m && y >= 0 && y < n && isInfected[x][y] == 0) {
							isInfected[x][y] = 1;
						}
					}
				}
			}
			groups = buildGroups(isInfected);
		}
		return walls;
	}

    private int dfs(int i, int j, int[][] isInfected, List<int[]> cells,
                    Set<Integer> next, boolean[][] visited) {
        int res = 0;
        visited[i][j] = true;
        cells.add(new int[]{i, j});
        int count = 0;
        for (int d = 0; d < 4; d++) {
            int x = i + DIRS[d], y = j + DIRS[d + 1];
            if (x >= 0 && x < isInfected.length && y >= 0 && y < isInfected[0].length) {
                if (isInfected[x][y] == 1 && !visited[x][y]) {
                    res += dfs(x, y, isInfected, cells, next, visited);
                } else if (isInfected[x][y] == 0) {
                    next.add(x * isInfected[0].length + y);
                    count++;
                }
            }
        }
        return res + count;
    }

    private List<Group> buildGroups(int[][] isInfected) {
        int m = isInfected.length, n = isInfected[0].length, max = 0, idx = -1;
        List<Group> groups = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) { // O(m * n)
            for (int j = 0; j < n; j++) {
                if (isInfected[i][j] == 1 && !visited[i][j]) {
                    int walls = 0;
                    List<int[]> cells = new ArrayList<>();
                    Set<Integer> next = new HashSet<>();
                    walls = dfs(i, j, isInfected, cells, next, visited);
                    groups.add(new Group(walls, next, cells));
                    if (next.size() > max) {
                        max = next.size();
                        idx = groups.size() - 1;
                    }
                }
            }
        }
        if (!groups.isEmpty() && idx != -1) {
            Group temp = groups.get(0);
            groups.set(0, groups.get(idx));
            groups.set(idx, temp);
        }
        return groups;
    }
}
