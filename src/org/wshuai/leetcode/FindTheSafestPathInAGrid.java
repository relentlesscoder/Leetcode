package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/30/2023.
 * #2812 https://leetcode.com/problems/find-the-safest-path-in-a-grid/
 */
public class FindTheSafestPathInAGrid {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(n^2 * log(n)), space O(n^2)
    public int maximumSafenessFactorDijkstra(List<List<Integer>> grid) {
        int n = grid.size();
        Deque<int[]> queue = new ArrayDeque<>();
        int[][] sf = new int[n][n];
        for (int i = 0; i < n; i++) { // O(n^2)
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    queue.offer(new int[]{i, j});
                } else {
                    sf[i][j] = -1;
                }
            }
        }
        // 用一个multi source BFS来计算所有格子的安全系数
        // 示例1:
        // [[0,0,1],
        //  [0,0,0],
        //  [1,0,0]]
        // 安全系数矩阵：
        // [[2,1,0],
        //  [1,2,1],
        //  [0,1,2]]
        int safety = 0;
        while (!queue.isEmpty()) { // O(n^2)
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && sf[x][y] == -1) {
                        queue.offer(new int[]{x, y});
                        sf[x][y] = safety + 1;
                    }
                }
            }
            safety++;
        }
        // 使用Dijkstra算法来计算最大安全系数，维护一个最大堆每次取出当前的最大安全系数
        // 进行计算。
        PriorityQueue<int[]> maxQueue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        maxQueue.offer(new int[]{sf[0][0], 0, 0});
        while (!maxQueue.isEmpty()) { // O(n^2 * log(n))
            int[] curr = maxQueue.poll();
            if (curr[1] == n - 1 && curr[2] == n - 1) {
                return curr[0];
            }
            for (int i = 0; i < 4; i++) {
                int x = curr[1] + DIRS[i], y = curr[2] + DIRS[i + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && sf[x][y] != -1) {
                    // 注意这里我们只能取当前值与相邻格子的最小值，因为整个路径的安全系数
                    // 是由最低的那个格子的安全系数决定的
                    maxQueue.offer(new int[]{Math.min(curr[0], sf[x][y]), x, y});
                    sf[x][y] = -1;
                }
            }
        }
        return 0;
    }

	// time O(n^2 * α(n^2)), space O(n^2)
	public int maximumSafenessFactorUnionFind(List<List<Integer>> grid) {
		int n = grid.size();
		List<int[]> queue = new ArrayList<>();
		int[][] sf = new int[n][n];
		for (int i = 0; i < n; i++) { // O(n^2)
			for (int j = 0; j < n; j++) {
				if (grid.get(i).get(j) == 1) {
					queue.add(new int[] {i, j});
				} else {
					sf[i][j] = -1;
				}
			}
		}
		List<List<int[]>> groups = new ArrayList<>();
		while (!queue.isEmpty()) { // O(n^2)
			List<int[]> temp = new ArrayList<>();
			for (int[] curr : queue) {
				for (int i = 0; i < 4; i++) {
					int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
					if (x >= 0 && x < n && y >= 0 && y < n && sf[x][y] == -1) {
						temp.add(new int[] {x, y});
						sf[x][y] = groups.size() + 1;
					}
				}
			}
			groups.add(queue);
			queue = temp;
		}
		UnionFind uf = new UnionFind(n * n);
		for (int s = groups.size() - 1; s >= 0; s--) { // O(n^2 * α(n))
			for (int[] curr : groups.get(s)) {
				for (int i = 0; i < 4; i++) {
					int x = curr[0] + DIRS[i], y = curr[1] + DIRS[i + 1];
					if (x >= 0 && x < n && y >= 0 && y < n && sf[x][y] >= sf[curr[0]][curr[1]]) {
						uf.union(curr[0] * n + curr[1], x * n + y);
					}
				}
			}
			if (uf.find(0) == uf.find(n * n - 1)) {
				return s;
			}
		}
		return 0;
	}

	private static class UnionFind {

		private final int[] roots;
		private final int[] ranks;

		public UnionFind(int n) {
			roots = new int[n];
			ranks = new int[n];
			Arrays.setAll(roots, i -> i);
			Arrays.fill(ranks, 1);
		}

		public int find(int x) {
			if (roots[x] != x) {
				roots[x] = find(roots[x]);
			}
			return roots[x];
		}

		public void union(int x, int y) {
			int rx = find(x), ry = find(y);
			if (rx == ry) {
				return;
			}
			if (ranks[rx] > ranks[ry]) {
				ranks[rx] += ranks[ry];
				roots[ry] = rx;
			} else {
				ranks[ry] += ranks[rx];
				roots[rx] = ry;
			}
		}
	}
}
