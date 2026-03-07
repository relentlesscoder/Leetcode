package org.wshuai.leetcode.graph;

import java.util.Arrays;

/**
 * Created by Wei on 09/29/2019.
 * #1101 https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/
 */
public class TheEarliestMomentWhenEveryoneBecomeFriends {

    // time O(n * α(n)), space O(n)
    public int earliestAcq(int[][] logs, int n) {
        // 对数组按时间戳从小到大排序，遍历数组连通每个日志的两个人。最早使得
        // 连通集数量变为1的那个日志的时间戳即为所求。
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        UnionFind uf = new UnionFind(n);
        for (int[] log : logs) {
            uf.union(log[1], log[2]);
            if (uf.count() == 1) {
                return log[0];
            }
        }
        return -1;
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
            this.count--;
        }

        public int count() {
            return this.count;
        }
    }
}


