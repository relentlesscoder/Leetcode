package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Created by Wei on 07/28/2017.
 * #0329 https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestIncreasingPathInAMatrix {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n), space O(m * n)
    public int longestIncreasingPathDFSWithMemorization(int[][] matrix) {
        // 记忆化搜索
        int res = 0, m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (memo[i][j] != 0) {
                    continue;
                }
                res = Math.max(res, dfs(i, j, matrix, memo));
            }
        }
        return res;
    }

    private int dfs(int i, int j, int[][] matrix, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        memo[i][j] = 1;
        for (int d = 0; d < 4; d++) {
            int x = i + DIRS[d], y = j + DIRS[d + 1];
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length
                    && matrix[x][y] > matrix[i][j]) {
                memo[i][j] = Math.max(memo[i][j], dfs(x, y, matrix, memo) + 1);
            }
        }
        return memo[i][j];
    }

    // time O(m * n), space O(m * n)
    public int longestIncreasingPathGreedyBFS(int[][] matrix) {
        // 创建一个 m x n 的距离矩阵dist, dist[i][j] 代表以元素 matrix[i][j]
        // 结尾的最长递增序列。从小到大遍历矩阵，每次尝试向相邻的格子扩展。如果能在
        // 当前格子的基础上构造一个更长的递增序列则更新距离矩阵的值。答案即为距离矩
        // 阵中的最大值。
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

    // time O(m * n), space O(m * n)
    public int longestIncreasingPathTopologicalSort(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // 如果一个方格 a 小于与之相邻的另一个方格 b 则存在从 a 到 b 的有向边, 统计每个方格的入度。
        int[][] indegree = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 4; d++) {
                    int x = i + DIRS[d], y = j + DIRS[d + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                        indegree[x][y]++;
                    }
                }
            }
        }
        // 拓扑排序所能进行的轮数即为最大递增路径的长度。
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (indegree[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                int i = curr[0], j = curr[1];
                for (int d = 0; d < 4; d++) {
                    int x = i + DIRS[d], y = j + DIRS[d + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                        if (--indegree[x][y] == 0) {
                            queue.offer(new int[]{x, y});
                        }
                    }
                }
            }
            res++;
        }
        return res;
    }
}
