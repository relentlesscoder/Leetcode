package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/22/2020.
 * #1627 https://leetcode.com/problems/graph-connectivity-with-threshold/
 */
public class GraphConnectivityWithThreshold {

    // time O(n * log(n)), space O(n)
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        if (threshold == 0) {
            Boolean[] arr = new Boolean[queries.length];
            Arrays.fill(arr, true);
            return Arrays.asList(arr);
        }
        UnionFind uf = new UnionFind(n + 1);
        for (int i = threshold + 1; i <= n; i++) {
            for (int j = 2 * i; j <= n; j += i) {
                uf.union(i, j);
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            res.add(uf.find(q[0]) == uf.find(q[1]));
        }
        return res;
    }

    private class UnionFind {

        private int[] root;
        private int[] rank;

        public UnionFind(int n) {
            root = new int[n];
            rank = new int[n];
            Arrays.fill(rank, 1);
            Arrays.setAll(root, i -> i);
        }

        public int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public void union(int x, int y) {
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
