package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 03/08/2020.
 * #1376 https://leetcode.com/problems/time-needed-to-inform-all-employees/
 */
public class TimeNeededToInformAllEmployees {

    private int res = 0;

    // time O(n), space O(n)
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        res = 0;
        // 构造邻接表
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            if (manager[i] == -1) {
                continue;
            }
            adj[manager[i]].add(i);
        }
        // DFS 计算最晚时间
        dfs(headID, -1, 0, adj, informTime);
        return res;
    }

    private void dfs(int node, int parent, int time, List<Integer>[] adj, int[] informTime) {
        res = Math.max(res, time);
        for (int next : adj[node]) {
            // 树的 DFS 只需要保证下一个节点不是父节点即可
            if (next == parent) {
                continue;
            }
            dfs(next, node, time + informTime[node], adj, informTime);
        }
    }
}
