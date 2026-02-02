package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 05/31/2020.
 * #1466 https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
 */
public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {

    // time O(n), space O(n)
    public int minReorderBFS(int n, int[][] connections) {
        // 因为每个城市到另一个城市只有一条路，实际上我们需要把这个图转化为以 0
        // 为根结点的树。
        int res = 0;
        List<int[]>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] conn : connections) {
			// 0 表示 inbound - 表示可以直接进入当前城市的相邻城市
            adj[conn[1]].add(new int[]{conn[0], 0});
			// 1 表示 outbound - 表示从当前城市出发可以到达的相邻城市
            adj[conn[0]].add(new int[]{conn[1], 1});
        }
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, -1});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0], parent = curr[1];
            // 把每个可以到达当前城市的相邻城市加入队列，这些城市不需要修改路径。把每个可以从
            // 当前城市到达的相邻城市加入队列，这些城市需要修改路径。注意因为题目条件相邻两个
            // 城市中只有一条路径所以 x 以及后续的城市想要到达 0 这条线路必须要改变方向。
            for (int[] next : adj[node]) {
				// 注意对于树的 BFS 或者 DFS ，只需要保证下一个节点不是前一个节点即可。因为
				// 只可能由一个节点进入当前节点所以不需要额外的数组去存已遍历的节点。
                if (next[0] == parent) {
                    continue;
                }
                res += next[1];
                queue.offer(new int[]{next[0], node});
            }
        }
        return res;
    }

    private int res = 0;

    // time O(n), space O(n)
    public int minReorderDFS(int n, int[][] connections) {
        res = 0;
        List<int[]>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] conn : connections) {
            adj[conn[1]].add(new int[]{conn[0], 0}); // 0 表示 inbound
            adj[conn[0]].add(new int[]{conn[1], 1}); // 1 表示 outbound
        }
        dfs(0, -1, adj);
        return res;
    }

    private void dfs(int node, int parent, List<int[]>[] adj) {
        for (int[] next : adj[node]) {
            if (next[0] == parent) {
                continue;
            }
            res += next[1]; // 需要将 outbound 的 edge 改成 inbound
            dfs(next[0], node, adj);
        }
    }
}
