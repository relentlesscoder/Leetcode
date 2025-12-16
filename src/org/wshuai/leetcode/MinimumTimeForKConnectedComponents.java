package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/16/2025.
 * #3608 https://leetcode.com/problems/minimum-time-for-k-connected-components/
 */
public class MinimumTimeForKConnectedComponents {

    // time O(n * log(n)), space O(n)
    public int minTime(int n, int[][] edges, int k) {
        int res = 0;
        Arrays.sort(edges, (a, b) -> b[2] - a[2]);
        UnionFind uf = new UnionFind(n);
        // 正难则反：按照时间点逆序遍历边集依次连通每条边，
        // 找到最后一条边使得连通分量的个数大于等于k，这个
        // 时间即为答案。
        for (int[] e : edges) {
            if (uf.countComponents() >= k) {
                res = e[2];
            } else {
                break;
            }
            uf.union(e[0], e[1]);
        }
        // Corner case：如果连通所有的边之后连通分量的个数
        // 依然大于等于k，那最小时间就是0。
        return uf.countComponents() >= k ? 0 : res;
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

        private int find(int x) {
            if (x != root[x]) {
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
            this.count--;
        }

        private int countComponents() {
            return this.count;
        }
    }
}
