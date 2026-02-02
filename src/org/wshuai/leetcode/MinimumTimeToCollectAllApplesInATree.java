package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 05/11/2020.
 * #1443 https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
 */
public class MinimumTimeToCollectAllApplesInATree {

    // time O(n), space O(n)
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        // 思路: 对于每个节点，如果以它为根结点的子树含有苹果或者它本身是苹果，则从它的父节点到它的边
        // 需要走两次 - 一来一回。我们可以用 DFS 来计算需要的总时间 (参考 Google drive 中的图)。
        // 构造邻接表
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        // DFS 计算总时间
        return dfs(0, -1, 0, adj, hasApple);
    }

    private int dfs(int node, int parent, int time, List<Integer>[] adj, List<Boolean> hasApple) {
        int res = 0;
        for (int next : adj[node]) {
            // 树的 DFS 只需要保证下一个节点不是父节点即可
            if (next == parent) {
                continue;
            }
            res += dfs(next, node, time + 1, adj, hasApple);
        }
        //   res > 0 表示子树中有苹果
        //   hasApple.get(node) 表示当前节点是苹果
        //   node != 0 表示非根结点
        if ((res > 0 || hasApple.get(node)) && node != 0) {
            res += 2;
        }
        return res;
    }
}
