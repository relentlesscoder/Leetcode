package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/18/2019.
 * #0959 https://leetcode.com/problems/regions-cut-by-slashes/
 */
public class RegionsCutBySlashes {

    // time O(n^2), space O(n^2)
    public int regionsBySlashes(String[] grid) {
        // 画图题：将每个格子用两条对角线切分成四个部分0，1，2，3，分别对
        // 应左，上，右，下. 则每个格子会有以下3种可能的情况：
        //   1. 格子值为空：那我们需要连通全部四个部分。
        //   2. 格子值为'\'：分别连通两对[0,3]和[1,2]。
        //   3. 格子值为'/'：分别连通两队[0,1]和[2,3]。
        // 除此之外，还需要对右边和下面的格子做以下操作：
        //   1. 如果右边的格子存在，则需要连通当前格子的2和右边格子的0。
        //   2. 如果下面的格子存在，则需要连通当前格子的3和下边格子的1。
        // 完成所有上述操作后，答案即为连通分量的总数量。
        int n = grid.length, m = n * n * 4;
        UnionFind uf = new UnionFind(m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                int idx = (i * n + j) * 4;
                if (c == ' ') {
                    uf.union(idx, idx + 1);
                    uf.union(idx, idx + 2);
                    uf.union(idx, idx + 3);
                } else if (c == '/') {
                    uf.union(idx, idx + 1);
                    uf.union(idx + 2, idx + 3);
                } else {
                    uf.union(idx, idx + 3);
                    uf.union(idx + 1, idx + 2);
                }
                if (i + 1 < n) {
                    uf.union(idx + 3, ((i + 1) * n + j) * 4 + 1);
                }
                if (j + 1 < n) {
                    uf.union(idx + 2, (i * n + j + 1) * 4);
                }
            }
        }
        return uf.countComponents();
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
