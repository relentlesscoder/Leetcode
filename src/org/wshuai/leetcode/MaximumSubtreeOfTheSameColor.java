package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 02/03/2026.
 * #3004 https://leetcode.com/problems/maximum-subtree-of-the-same-color/
 */
public class MaximumSubtreeOfTheSameColor {

    private int res = 0;

    // time O(n), space O(n)
    public int maximumSubtreeSize(int[][] edges, int[] colors) {
        res = 0;
        int n = edges.length + 1;
        // 构造邻接表
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        dfs(0, -1, adj, colors);
        return res;
    }

    private int[] dfs(int node, int parent, List<Integer>[] adj, int[] colors) {
        // res[0] 表示子树中的节点是否都是同一颜色 - 1 表示是而 0 表示否
        // res[1] 表示子树的颜色
        // res[2] 表示子树的节点数量
        int color = colors[node], good = 1, size = 1;
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            int[] ans = dfs(next, node, adj, colors);
            int g = ans[0], c = ans[1], s = ans[2];
            size += s;
            // 如果子树所有的节点颜色不一致或者跟当前节点不一样则以当前节点为根结点的子树肯定不符合要求
            if (g == 0 || c != color) {
                good = 0;
            }
        }
        // 如果符合要求且更大则更新答案
        if (good == 1 && size > res) {
            res = size;
        }
        return new int[]{good, color, size};
    }
}
