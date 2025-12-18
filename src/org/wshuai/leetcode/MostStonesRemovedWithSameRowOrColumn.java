package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/11/2019.
 * #0947 https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 */
public class MostStonesRemovedWithSameRowOrColumn {

    // time O(n), space O(n)
    public int removeStones(int[][] stones) {
        // 遍历数组，分别连通数组里具有相同横坐标和纵坐标的数。对于每个
        // 连通分量，仅需保留一个数而删除其他。所以答案即为数组里元素的
        // 个数减去连通分量的个数。
        int n = stones.length, max = (int) 1e4 + 1;
        UnionFind uf = new UnionFind(n);
        int[] row = new int[max];
        int[] col = new int[max];
        Arrays.fill(row, -1);
        Arrays.fill(col, -1);
        for (int i = 0; i < stones.length; i++) {
            int r = stones[i][0];
            if (row[r] == -1) {
                row[r] = i;
            } else {
                uf.union(row[r], i);
            }
        }
        for (int i = 0; i < stones.length; i++) {
            int c = stones[i][1];
            if (col[c] == -1) {
                col[c] = i;
            } else {
                uf.union(col[c], i);
            }
        }
        return n - uf.countComponents();
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
