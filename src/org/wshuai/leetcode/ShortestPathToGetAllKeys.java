package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/23/2019.
 * #0864 https://leetcode.com/problems/shortest-path-to-get-all-keys/
 */
public class ShortestPathToGetAllKeys {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n * k), space O(m * n * k)
    public int shortestPathAllKeys(String[] grid) {
        int steps = 0, m = grid.length, n = grid[0].length(), count = 0, sx = 0, sy = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c >= 'a' && c <= 'z') {
                    count++; // 计算钥匙的总数
                } else if (c == '@') { // 记录起始位置
                    sx = i;
                    sy = j;
                }
            }
        }
        int k = 1 << count;
        // 状态分别为当前横坐标，纵坐标和代表以获取钥匙的位掩码
        boolean[][][] visited = new boolean[m][n][k];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx, sy, 0});
        visited[sx][sy][0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                if (curr[2] == k - 1) {
                    return steps;
                }
                for (int d = 0; d < 4; d++) {
                    int x = curr[0] + DIRS[d], y = curr[1] + DIRS[d + 1], s = curr[2];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x].charAt(y) != '#') {
                        char c = grid[x].charAt(y);
                        // 下一步为空房间或者起始点，只用判断这个点是否遍历过
                        if ((c == '.' || c == '@') && !visited[x][y][s]) {
                            queue.offer(new int[]{x, y, s});
                            visited[x][y][s] = true;
                        } else if (c >= 'a' && c <= 'z') { // 下一步为钥匙，更新钥匙状态掩码
                            s |= 1 << (c - 'a');
                            if (!visited[x][y][s]) {
                                queue.offer(new int[]{x, y, s});
                                visited[x][y][s] = true;
                            }
                            // 下一步是上锁的房间，需要判断是否已拿到相应的钥匙
                        } else if (c >= 'A' && c <= 'Z' && (s & (1 << (c - 'A'))) > 0 && !visited[x][y][s]) {
                            queue.offer(new int[]{x, y, s});
                            visited[x][y][s] = true;
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
