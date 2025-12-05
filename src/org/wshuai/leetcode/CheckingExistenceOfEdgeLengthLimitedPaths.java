package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/30/2023.
 * #1697 https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/
 */
public class CheckingExistenceOfEdgeLengthLimitedPaths {

    // time O(n + e * log(e) + m * log(m) + (e + m) * α(n)), space O(n + m + e)
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        UnionFind uf = new UnionFind(n);
        int m = queries.length;
        boolean[] res = new boolean[m];
        int[][] sortedQueries = new int[m][4];
        for (int i = 0; i < m; i++) {
            sortedQueries[i] = new int[]{queries[i][0], queries[i][1], queries[i][2], i};
        }
        Arrays.sort(sortedQueries, (a, b) -> a[2] - b[2]);
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        for (int i = 0, j = 0; i < m; i++) {
            int[] q = sortedQueries[i];
            while (j < edgeList.length && edgeList[j][2] < q[2]) {
                int[] edge = edgeList[j++];
                uf.union(edge[0], edge[1]);
            }
            res[q[3]] = (uf.find(q[0]) == uf.find(q[1]));
        }
        return res;
    }

    private class UnionFind {

        private int[] root;

        private int[] rank;

        private int n;

        private UnionFind(int n) {
            this.n = n;
            this.root = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        private int find(int x) {
            if (root[x] != x) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        private void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (rank[rootX] > rank[rootY]) {
                rank[rootX] += rank[rootY];
                root[rootY] = rootX;
            } else {
                rank[rootY] += rank[rootX];
                root[rootX] = rootY;
            }
        }
    }
}
