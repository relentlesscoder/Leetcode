package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 12/03/2019.
 * #1273 https://leetcode.com/problems/delete-tree-nodes/
 */
public class DeleteTreeNodes {

    // time O(n), space O(n)
    public int deleteTreeNodesDFS(int nodes, int[] parent, int[] value) {
        // 构造邻接表
        List<Integer>[] adj = new ArrayList[nodes];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 1; i < parent.length; i++) {
            adj[parent[i]].add(i);
        }
        return dfs(0, adj, value)[0];
    }

    private int[] dfs(int node, List<Integer>[] adj, int[] value) {
        // res[0] - 节点数量; res[1] = 节点和
        int sum = value[node], cnt = 1;
        for (int next : adj[node]) {
            int[] ans = dfs(next, adj, value);
            cnt += ans[0];
            sum += ans[1];
        }
        // 如果节点和是 0 则将节点数目设为 0 以实现删除。
        return new int[]{sum == 0 ? 0 : cnt, sum};
    }
}
