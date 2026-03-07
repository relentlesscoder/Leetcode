package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 02/03/2026.
 * #3558 https://leetcode.com/problems/number-of-ways-to-assign-edge-weights-i/
 */
public class NumberOfWaysToAssignEdgeWeightsI {

    private static final int MOD = (int) 1e9 + 7;

    private static final int UPPER = (int) 1e5 + 1;

    private static final long[] FACTORIAL = new long[UPPER];

    private static final long[] INV_FACTORIAL = new long[UPPER];

    static {
        FACTORIAL[0] = 1;
        for (int i = 1; i < UPPER; i++) {
            FACTORIAL[i] = FACTORIAL[i - 1] * i % MOD;
        }
        INV_FACTORIAL[UPPER - 1] = pow(FACTORIAL[UPPER - 1], MOD - 2);
        for (int i = UPPER - 1; i > 0; i--) {
            INV_FACTORIAL[i - 1] = INV_FACTORIAL[i] * i % MOD;
        }
    }

    // time O(n), space O(n)
    public int assignEdgeWeights(int[][] edges) {
        long res = 0L;
        int n = edges.length + 1;
        // 构造邻接表
        List<Integer>[] adj = new ArrayList[n + 1];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        // DFS 寻找根结点到最深节点的边的数量
        int depth = dfs(1, 0, adj) - 1;
        // 计算赋值为 1 或 2 的边的组合
        for (int i = 1; i <= depth; i++) {
            int sum = i + (depth - i) * 2;
            if ((sum & 1) == 1) {
                res += combination(i, depth);
            }
        }
        return (int) (res % MOD);
    }

    private static long pow(long x, int n) {
        long res = 1L;
        while (n > 0) {
            if (n % 2 == 1) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n /= 2;
        }
        return res;
    }

    private long combination(int p, int q) {
        return FACTORIAL[q] * INV_FACTORIAL[q - p] % MOD * INV_FACTORIAL[p] % MOD;
    }

    private int dfs(int node, int parent, List<Integer>[] adj) {
        int max = 0;
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            max = Math.max(max, dfs(next, node, adj));
        }
        return 1 + max;
    }
}
