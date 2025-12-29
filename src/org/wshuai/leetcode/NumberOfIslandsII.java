package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 07/20/2017.
 * #0305 https://leetcode.com/problems/number-of-islands-ii/
 */
public class NumberOfIslandsII {
    private static final int[] DIRS = new int[]{0, 1, 0, -1, 0};

    // time O(k * α(m * n)), space O(m * n)
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        // 维护一个并查集，当每次添加一块陆地，使用并查集与相邻已经存在的陆地向连通。
        List<Integer> res = new ArrayList<>();
		UnionFind uf = new UnionFind(m * n);
        for (int[] p : positions) {
            int idx = p[0] * n + p[1];
            // 直接返回如果当前位置陆地已经存在
            if (uf.exists(idx)) {
                res.add(uf.countLands());
                continue;
            }
            // 添加新的陆地
            uf.addLand(idx);
            for (int d = 0; d < 4; d++) {
                int x = p[0] + DIRS[d], y = p[1] + DIRS[d + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && uf.exists(x * n + y)) {
                    uf.union(idx, x * n + y);
                }
            }
            res.add(uf.countLands());
        }
        return res;
    }

    private static class UnionFind {

        private int lands;
        private final int[] roots;
        private final int[] ranks;

        public UnionFind(int n) {
            roots = new int[n];
            ranks = new int[n];
            Arrays.setAll(roots, i -> -1);
            lands = 0;
        }

        public void union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) {
                return;
            }
            if (ranks[ra] > ranks[rb]) {
                ranks[ra] += ranks[rb];
                roots[rb] = ra;
            } else {
                ranks[rb] += ranks[ra];
                roots[ra] = rb;
            }
            lands--;
        }

        private int find(int a) {
            if (roots[a] != a) {
                roots[a] = find(roots[a]);
            }
            return roots[a];
        }

        public void addLand(int a) {
            lands++;
            ranks[a] = 1;
            roots[a] = a;
        }

        public boolean exists(int a) {
            return roots[a] != -1;
        }

        public int countLands() {
            return lands;
        }
    }
}
