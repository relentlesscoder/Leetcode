package org.wshuai.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 02/01/2026.
 * #3528 https://leetcode.com/problems/unit-conversion-i/
 */
public class UnitConversionI {

    private static final int MOD = (int) 1e9 + 7;

    // time O(n), space O(n)
    public int[] baseUnitConversions(int[][] conversions) {
        // 树的标志是 n 个节点有 n - 1 条边
        int n = conversions.length + 1;
        int[] res = new int[n];
        // 构造邻接表
        List<int[]>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] conv : conversions) {
            adj[conv[0]].add(new int[]{conv[1], conv[2]});
        }
        // DFS 计算以 0 为 base 的 unit 值
        dfs(0, -1, 1L, res, adj);
        return res;
    }

    private void dfs(int node, int parent, long prod, int[] res, List<int[]>[] adj) {
        res[node] = (int) prod;
        for (int[] next : adj[node]) {
            // 树的 DFS 只需要保证下一个节点不是父节点即可
            if (next[0] == parent) {
                continue;
            }
            dfs(next[0], node, prod * next[1] % MOD, res, adj);
        }
    }
}
