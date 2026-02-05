package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 02/04/2026.
 * #3203 https://leetcode.com/problems/find-minimum-diameter-after-merging-two-trees/
 */
public class FindMinimumDiameterAfterMergingTwoTrees {

    private int d = 0;

    // time O(n), space O(n)
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        // 思路: 要使得连接后的直径最小，则需要找到两个最小高度树的根结点并将他们相连得到最小直径。
        // 可用反证法证明，假如对于树 1 与树 2 相连的节点不是最小高度树的根结点则以这个选择的节点
        // 的到的最大深度必然大于最小高度树的最大深度，则得到的直径自然也就不是最小直径。
        // 本题注意这个相连后的经过两个根结点的直径并不一定是新树的直径，新树的直径叶有可能是原来两
        // 个树自身的直径所以答案要取这三个的最大值。
        d = 0; // 两树直径的较大值
        int n = edges1.length + 1, m = edges2.length + 1;
        // 找到 edges1 的最小高度树的最大深度
        int r1 = findMinHeight(n, edges1);
        // 找到 edges2 的最小高度树的最大深度
        int r2 = findMinHeight(m, edges2);
        // 连接两个最小高度树的根结点形成最小直径 r1 + r2 + 1，注意还需与两树自身直径的较大值
        // 比较的到最终答案。
        return Math.max(r1 + r2 + 1, d);
    }

    public int findMinHeight(int n, int[][] edges) {
        // 同 #0310 - 找到 edges 能形成的最小高度树的根结点
        if (n == 1) {
            return 0;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        // 构造邻接表和节点度的数组
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        int[] degree = new int[n];
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
            degree[e[0]]++;
            degree[e[1]]++;
        }
        // 将度为 1 的节点入列
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        // 拓扑排序
        while (!queue.isEmpty()) {
            int size = queue.size();
            res.clear();
            while (size-- > 0) {
                int node = queue.poll();
                res.add(node);
                for (int next : adj[node]) {
                    // 将度为 1 的节点入列
                    if (--degree[next] == 1) {
                        queue.offer(next);
                    }
                }
            }
        }
        // 对根结点进行 DFS 寻找树的深度以及树的直径
        return dfs(res.get(0), -1, adj) - 1;
    }

    private int dfs(int node, int parent, List<Integer>[] adj) {
        // 同 #1245 - 树的直径
        int max = 0;
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            int len = dfs(next, node, adj);
            d = Math.max(d, max + len);
            max = Math.max(max, len);
        }
        return max + 1;
    }
}
