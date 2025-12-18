package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/08/2023.
 * #2076 https://leetcode.com/problems/process-restricted-friend-requests/
 */
public class ProcessRestrictedFriendRequests {

    // time O(m * k + n), space O(m + n)
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        int m = requests.length;
        boolean[] res = new boolean[m];
        UnionFind uf = new UnionFind(n); // O(n)
        // 遍历请求数组
        for (int i = 0; i < m; i++) { // O(m)
            int[] r = requests[i];
            // 注意这里要找到节点所在的连通分量的根节点
            int r1 = uf.find(r[0]), r2 = uf.find(r[1]);
            boolean valid = true;
            // 对每一个交友请求遍历限制数组
            for (int[] re : restrictions) { // O(k)
                // 注意这里要找到限制节点所在的连通分量的根节点
                int r3 = uf.find(re[0]), r4 = uf.find(re[1]);
                // 如果当前请求的两个连通分量存在于限制数组，那么请求不合法
                if ((r3 == r1 && r4 == r2) || (r3 == r2 && r4 == r1)) {
                    valid = false;
                    break;
                }
            }
            // 如果当前请求合法，连通这两个连通分量
            if (valid) {
                uf.union(r[0], r[1]);
            }
            res[i] = valid;
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
        }
    }
}
