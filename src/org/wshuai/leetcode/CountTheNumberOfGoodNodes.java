package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 02/03/2026.
 * #3249 https://leetcode.com/problems/count-the-number-of-good-nodes/
 */
public class CountTheNumberOfGoodNodes {

    private int res = 0;

    // time O(n), space O(n)
    public int countGoodNodes(int[][] edges) {
        res = 0;
        int n = edges.length + 1;
        // 构造邻接表
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        dfs(0, -1, adj);
        return res;
    }

    private int dfs(int node, int parent, List<Integer>[] adj) {
        int pre = -1, count = 1;
        boolean isGood = true;
        for (int next : adj[node]) {
            // 树的 DFS 只需要保证下一个节点不是父节点即可
            if (next == parent) {
                continue;
            }
            // DFS 找子树的大小
            int size = dfs(next, node, adj);
            if (pre == -1) {
                pre = size;
            } else if (size != pre) { // 判断是否为好节点
                isGood = false;
            }
            count += size;
        }
        res += isGood ? 1 : 0;
        return count; //返回当前子树的大小
    }
}
