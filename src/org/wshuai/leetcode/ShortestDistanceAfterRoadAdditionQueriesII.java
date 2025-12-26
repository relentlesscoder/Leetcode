package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/25/2025.
 * #3244 https://leetcode.com/problems/shortest-distance-after-road-addition-queries-ii/
 */
public class ShortestDistanceAfterRoadAdditionQueriesII {

    // time O(n + m), space O(n)
    public int[] shortestDistanceAfterQueriesUnionFind(int n, int[][] queries) {
        // 对于所有边0到n - 1维护一个并查集
        UnionFind uf = new UnionFind(n - 1);
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int u = queries[i][0], v = queries[i][1] - 1;
            // 连通节点u到v相当于把边u, u + 1 ... v - 2与边v - 1连通
            uf.connect(u, v);
            // 此时从0到n - 1的最短路径即为连通分量的数量
            res[i] = uf.countComponents();
        }
        return res;
    }

    private class UnionFind {

        private int count;
        private int[] root;

        public UnionFind(int n) {
            this.count = n;
            root = new int[n];
            Arrays.setAll(root, i -> i);
        }

        public int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public void connect(int x, int y) {
            int ry = find(y);
            // 为避免逐条边连通导致的超时，使用根结点来实现跳跃
            for (int i = find(x); i < y; i = find(i + 1)) {
                // 连通时总是把根结点设为最后一条边
                root[i] = ry;
                this.count--;
            }
        }

        public int countComponents() {
            return this.count;
        }
    }

    // time O(n + m), space O(n)
    public int[] shortestDistanceAfterQueriesRecordJumpPoint(int n, int[][] queries) {
        int dist = n - 1, m = queries.length;
        int[] res = new int[m], next = new int[n];
        for (int i = 0; i < n - 1; i++) {
            next[i] = i + 1;
        }
        for (int i = 0; i < m; i++) {
            int u = queries[i][0], v = queries[i][1];
            // 如果当前询问的跳跃终点比原来的远，删除之前所有[u,v]中间的跳跃点
            if (next[u] > 0 && next[u] < v) {
                for (int j = next[u]; j < v; ) {
                    dist--; // 每删除一次中间点等于减少一步
                    int tmp = next[j];
                    next[j] = 0;
                    j = tmp; // 跳跃到下一个点
                }
                // 把u的跳跃终点设为v
                next[u] = v;
            }
            res[i] = dist;
        }
        return res;
    }
}
