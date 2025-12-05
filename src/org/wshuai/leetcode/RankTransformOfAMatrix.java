package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/04/2025.
 * #1632 https://leetcode.com/problems/rank-transform-of-a-matrix/
 */
public class RankTransformOfAMatrix {

    // time O(m * n * max(log(m), log(n))), space O(m * n)
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, t = m * n;
        UnionFind uf = new UnionFind(t); // O(m * n)
        // O(m * n * α(n))
        for (int i = 0; i < m; i++) {
            Map<Integer, List<Integer>> groups = new HashMap<>();
            for (int j = 0; j < n; j++) {
                groups.computeIfAbsent(matrix[i][j], key -> new ArrayList<>()).add(i * n + j);
            }
            for (Map.Entry<Integer, List<Integer>> entry : groups.entrySet()) {
                List<Integer> vals = entry.getValue();
                for (int k = 1; k < vals.size(); k++) {
                    uf.union(vals.get(k - 1), vals.get(k));
                }
            }
        }
        // O(m * n * α(m * n))
        for (int j = 0; j < n; j++) {
            Map<Integer, List<Integer>> groups = new HashMap<>();
            for (int i = 0; i < m; i++) {
                groups.computeIfAbsent(matrix[i][j], key -> new ArrayList<>()).add(i * n + j);
            }
            for (Map.Entry<Integer, List<Integer>> entry : groups.entrySet()) {
                List<Integer> vals = entry.getValue();
                for (int k = 1; k < vals.size(); k++) {
                    uf.union(vals.get(k - 1), vals.get(k));
                }
            }
        }

        int[] indegree = new int[t];
        List<Integer>[] adj = new ArrayList[t];
        Arrays.setAll(adj, index -> new ArrayList<>());

        // O(m * n * log(n) + m * n * α(m * n))
        for (int i = 0; i < m; ++i) {
            int[][] sorted = new int[n][2];
            for (int j = 0; j < n; ++j) {
                sorted[j][0] = matrix[i][j];
                sorted[j][1] = j;
            }
            Arrays.sort(sorted, (a, b) -> a[0] - b[0]);
            for (int j = 1; j < n; ++j) {
                if (sorted[j - 1][0] != sorted[j][0]) {
                    int u = uf.find(i * n + sorted[j - 1][1]);
                    int v = uf.find(i * n + sorted[j][1]);
                    indegree[v]++;
                    adj[u].add(v);
                }
            }
        }

        // O(n * m * log(m) + m * n * α(m * n))
        for (int i = 0; i < n; ++i) {
            int[][] sorted = new int[m][2];
            for (int j = 0; j < m; ++j) {
                sorted[j][0] = matrix[j][i];
                sorted[j][1] = j;
            }
            Arrays.sort(sorted, (a, b) -> a[0] - b[0]);
            for (int j = 1; j < m; ++j) {
                if (sorted[j - 1][0] != sorted[j][0]) {
                    int u = uf.find(sorted[j - 1][1] * n + i);
                    int v = uf.find(sorted[j][1] * n + i);
                    indegree[v]++;
                    adj[u].add(v);
                }
            }
        }

        // O(m * n)
        int[] rank = new int[t];
        Arrays.fill(rank, 1);
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < t; i++) {
            if (uf.find(i) == i && indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj[u]) {
                rank[v] = Math.max(rank[v], rank[u] + 1);
                indegree[v]--;
                if (indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = rank[uf.find(i * n + j)];
            }
        }
        return matrix;
    }

    private static class UnionFind {

        private int[] root;

        private int[] rank;

        public UnionFind(int n) {
            root = new int[n];
            rank = new int[n];
            Arrays.setAll(root, index -> index);
            Arrays.setAll(rank, index -> 1);
        }

        public int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return;
            }
            if (rank[rx] > rank[ry]) {
                rank[rx] += rank[ry];
                root[ry] = rx;
            } else {
                rank[ry] += rank[rx];
                root[rx] = ry;
            }
        }
    }
}
