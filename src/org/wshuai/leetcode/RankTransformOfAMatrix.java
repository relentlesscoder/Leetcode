package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 11/04/2025.
 * #1632 https://leetcode.com/problems/rank-transform-of-a-matrix/
 */
public class RankTransformOfAMatrix {

    // time O(m * n * log(n) + m * n * log(m)), space O(m * n)
    public int[][] matrixRankTransformTopologicalSort(int[][] matrix) {
        // 利用拓扑排序来确定格子的相对位置
        int m = matrix.length, n = matrix[0].length, t = m * n;
        UnionFind uf = new UnionFind(t);
        // 连通每一行上相同的值
        for (int i = 0; i < m; i++) { // O(m * n)
            Map<Integer, List<Integer>> groups = new HashMap<>();
            for (int j = 0; j < n; j++) {
                groups.computeIfAbsent(matrix[i][j], k -> new ArrayList<>()).add(i * n + j);
            }
            for (List<Integer> cells : groups.values()) {
                for (int k = 1; k < cells.size(); k++) {
                    uf.union(cells.get(k - 1), cells.get(k));
                }
            }
        }
        // 连通每一列上相同的值
        for (int j = 0; j < n; j++) { // O(m * n)
            Map<Integer, List<Integer>> groups = new HashMap<>();
            for (int i = 0; i < m; i++) {
                groups.computeIfAbsent(matrix[i][j], k -> new ArrayList<>()).add(i * n + j);
            }
            for (List<Integer> cells : groups.values()) {
                for (int k = 1; k < cells.size(); k++) {
                    uf.union(cells.get(k - 1), cells.get(k));
                }
            }
        }
        // 拓扑排序
        int[] indegree = new int[t];
        List<Integer>[] adj = new ArrayList[t];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 0; i < m; i++) { // O(m * n * log(n))
            int[][] sorted = new int[n][2];
            for (int j = 0; j < n; j++) {
                sorted[j][0] = matrix[i][j];
                sorted[j][1] = j;
            }
            Arrays.sort(sorted, (a, b) -> a[0] - b[0]);
            for (int j = 1; j < n; j++) {
                if (sorted[j][0] != sorted[j - 1][0]) {
                    int v = uf.find(i * n + sorted[j][1]), u = uf.find(i * n + sorted[j - 1][1]);
                    indegree[v]++;
                    adj[u].add(v);
                }
            }
        }
        for (int j = 0; j < n; j++) { // O(n * m * log(m))
            int[][] sorted = new int[m][2];
            for (int i = 0; i < m; i++) {
                sorted[i][0] = matrix[i][j];
                sorted[i][1] = i;
            }
            Arrays.sort(sorted, (a, b) -> a[0] - b[0]);
            for (int i = 1; i < m; i++) {
                if (sorted[i][0] != sorted[i - 1][0]) {
                    int v = uf.find(sorted[i][1] * n + j), u = uf.find(sorted[i - 1][1] * n + j);
                    indegree[v]++;
                    adj[u].add(v);
                }
            }
        }
        int[] rank = new int[t];
        Arrays.fill(rank, 1);
        Deque<Integer> queue = new ArrayDeque<>();
        // 注意这里只对每个连通分量的根结点进行拓扑排序
        for (int i = 0; i < t; i++) { // O(m * n)
            if (uf.find(i) == i && indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) { // O(m * n)
            int curr = queue.poll();
            for (int next : adj[curr]) {
                // 下一个节点的秩的值为当前节点的值加一
                rank[next] = Math.max(rank[next], rank[curr] + 1);
                if (--indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) { // O(m * n)
            for (int j = 0; j < n; j++) {
                res[i][j] = rank[uf.find(i * n + j)];
            }
        }
        return res;
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

    // time O(m * n * log(m + n) + k * log(k)), space O(m * n)
    public int[][] matrixRankTransformUnionFind(int[][] matrix) {
        // 从小到大遍历元素，连通行和列来确定格子的相对位置
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[m][n];
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < m; i++) { // O(m * n)
            for (int j = 0; j < n; j++) {
                map.computeIfAbsent(matrix[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
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
