package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/14/2025.
 * #3532 https://leetcode.com/problems/path-existence-queries-in-a-graph-i/
 */
public class PathExistenceQueriesInAGraphI {

    // time O(n + m), space O(n)
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // 给数组分组编号
        int m = queries.length;
        int[] ids = new int[n];
        for (int i = 1; i < n; i++) {
            ids[i] = ids[i - 1]; // “继承”前一个元素的组号
            // 如果当前元素与前一个元素差值大于最大差值，编号加一
            if (nums[i] - nums[i - 1] > maxDiff) {
                ids[i]++;
            }
        }
        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            res[i] = ids[q[0]] == ids[q[1]];
        }
        return res;
    }

    // time O((n + m) * α(n)), space O(n)
    public boolean[] pathExistenceQueriesUnionFind(int n, int[] nums, int maxDiff, int[][] queries) {
        int m = queries.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 1; i < n; i++) {
            // 逐对连通相邻元素如果他们的差在最大差值之内，注意最后的连通集内可
            // 能存在差值大于最大差值的元素对。
            // 示例1: [1,2,3,4,5,6] maxDiff = 2 所有元素都在同一个连通集内
            if (nums[i] - nums[i - 1] <= maxDiff) {
                uf.union(i, i - 1);
            }
        }
        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            res[i] = uf.find(q[0]) == uf.find(q[1]);
        }
        return res;
    }

    private static class UnionFind {

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
