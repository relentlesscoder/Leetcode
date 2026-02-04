package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 02/03/2026.
 * #3593 https://leetcode.com/problems/minimum-increments-to-equalize-leaf-paths/
 */
public class MinimumIncrementsToEqualizeLeafPaths {

    private int res = 0;

    // time O(n), space O(n)
    public int minIncrease(int n, int[][] edges, int[] cost) {
        // #2673 相似题。 要使得所有根到叶的路径和一样，则从最低的非叶节点开始，它们所有的子节点
        // (都是叶子节点) 必须修改成同样的值 - 因为从根节点到当前节点的路径是共享的。这样最小的代
        // 价就是将所有叶子节点修改为等于这些叶子节点里面最大的那个值。然后把这个值层层向上传递，对
        // 每一层的父节点都需要保证所有子节点的值都相同。
        res = 0;
        // 构造邻接表
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        adj[0].add(-1);
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        dfs(0, -1, adj, cost);
        return res;
    }

    private long dfs(int node, int parent, List<Integer>[] adj, int[] cost) {
        long max = 0L; // 子节点修改后的最大值
        int cnt = 0; // 等于最大值的子节点个数
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            // 递归计算子节点修改后的值
            long val = dfs(next, node, adj, cost);
            if (val > max) {
                max = val;
                cnt = 1;
            } else if (val == max) {
                cnt++;
            }
        }
        // 将需要修改的节点树统计到答案中
        res += (!adj[node].isEmpty() ? adj[node].size() - 1 - cnt : 0);
        return max + cost[node]; // 将最大值向上传递
    }
}
