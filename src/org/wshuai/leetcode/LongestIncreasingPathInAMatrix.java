package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 07/28/2017.
 * #0329 https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestIncreasingPathInAMatrix {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n), space O(m * n)
    public int longestIncreasingPathDFS(int[][] matrix) {
        int res = 0, m = matrix.length, n = matrix[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(i, j, matrix, dist));
            }
        }
        return res;
    }

    private int dfs(int i, int j, int[][] matrix, int[][] dist) {
        if (dist[i][j] != 0) {
            return dist[i][j];
        }
        dist[i][j] = 1;
        for (int d = 0; d < 4; d++) {
            int x = i + DIRS[d], y = j + DIRS[d + 1];
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length
					&& matrix[x][y] > matrix[i][j]) {
                dist[i][j] = Math.max(dist[i][j], dfs(x, y, matrix, dist) + 1);
            }
        }
        return dist[i][j];
    }

    // time O(m * n), space O(m * n)
    public int longestIncreasingPathGreedy(int[][] matrix) {
        // 创建一个m x n的距离矩阵dist, dist[i][j]代表以元素matrix[i][j]结尾的
        // 最长递增序列。从小到大遍历矩阵，每次尝试向相邻的格子扩展。如果能在当前格子
        // 的基础上构造一个更长的递增序列则更新距离矩阵的值。答案即为距离矩阵中的最大
        // 值。
        int res = 1, m = matrix.length, n = matrix[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, 1);
        }
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                minQueue.offer(new int[]{i, j, matrix[i][j]});
            }
        }
        while (!minQueue.isEmpty()) {
            int[] curr = minQueue.poll();
            int i = curr[0], j = curr[1];
            for (int d = 0; d < 4; d++) {
                int x = i + DIRS[d], y = j + DIRS[d + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                    if (dist[x][y] < dist[i][j] + 1) {
                        dist[x][y] = dist[i][j] + 1;
                        res = Math.max(res, dist[x][y]);
                    }
                }
            }
        }
        return res;
    }
}
