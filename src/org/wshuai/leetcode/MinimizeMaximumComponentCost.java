package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/16/2025.
 * #3613 https://leetcode.com/problems/minimize-maximum-component-cost/
 */
public class MinimizeMaximumComponentCost {

    // time O(n + m * log(m)), space O(n)
    public int minCost(int n, int[][] edges, int k) {
        // #3608相似题
        int res = Integer.MAX_VALUE, m = edges.length;
        UnionFind uf = new UnionFind(n);
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        for (int i = 0; i < m; i++) {
            int[] curr = edges[i];
            uf.union(curr[0], curr[1]);
            if (uf.countComponents() == k) {
                res = Math.min(res, curr[2]);
                break;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    private static class UnionFind {

        private int count;
        private int[] root;
        private int[] rank;

        public UnionFind(int n) {
            this.count = n;
            root = new int[n];
            rank = new int[n];
            Arrays.fill(rank, 1);
            Arrays.setAll(root, i -> i);
        }

        public int countComponents() {
            return this.count;
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
            this.count--;
        }

        private int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }
    }
}
