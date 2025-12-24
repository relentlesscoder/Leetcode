package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Wei on 11/17/2023.
 * #2371 https://leetcode.com/problems/minimize-maximum-value-in-a-grid/
 */
public class MinimizeMaximumValueInAGrid {

    // time O(m * n * log(m * n)), space O(m * n)
    public int[][] minScore(int[][] grid) {
        // #1632相似题, 不同点是此题无需计算整个网格的秩
        int m = grid.length, n = grid[0].length;
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                minQueue.offer(new int[]{grid[i][j], i, j});
            }
        }
        int[] rowMax = new int[m], colMax = new int[n];
        while (!minQueue.isEmpty()) {
            int[] curr = minQueue.poll();
            int val = Math.max(rowMax[curr[1]], colMax[curr[2]]) + 1;
            rowMax[curr[1]] = val;
            colMax[curr[2]] = val;
            grid[curr[1]][curr[2]] = val;
        }
        return grid;
    }

    // time O(m * n * log(m + n) + k * log(k)), space O(m * n)
    public int[][] minScoreUnionFind(int[][] grid) {
        // #1632相似题
        // 从小到大遍历元素，连通行和列来确定格子的相对位置
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m][n];
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < m; i++) { // O(m * n)
            for (int j = 0; j < n; j++) {
                map.computeIfAbsent(grid[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        List<Integer> keys = new ArrayList(map.keySet());
        Collections.sort(keys); // O(k * log(k))
        int[] roots = new int[m + n], ranks = new int[m + n];
        // 从小到大遍历矩阵中的元素
        for (int key : keys) {
            List<int[]> cells = map.get(key);
            Arrays.setAll(roots, i -> i);
            int[] next = ranks.clone();
            // 对每一个格子，连通它的行和列
            for (int[] c : cells) { // total O(m * n)
                int rx = find(roots, c[0]), ry = find(roots, c[1] + m); // log(m + n)
                if (rx != ry) {
                    roots[ry] = rx;
                    // 秩取行和列的最大值，注意这里不能立即更新
                    next[rx] = Math.max(next[rx], next[ry]);
                }
            }
            for (int[] c : cells) { // total O(m * n)
                int x = c[0], y = c[1];
                // 把秩的值加一
                int rank = next[find(roots, x)] + 1;
                res[x][y] = rank;
                ranks[x] = ranks[y + m] = rank; // 更新行和列的秩的值
            }
        }
        return res;
    }

    private int find(int[] roots, int x) {
        if (x != roots[x]) {
            roots[x] = find(roots, roots[x]);
        }
        return roots[x];
    }
}
