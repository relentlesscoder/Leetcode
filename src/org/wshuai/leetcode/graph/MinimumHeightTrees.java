package org.wshuai.leetcode.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 03/20/2017.
 * #0310 https://leetcode.com/problems/minimum-height-trees/
 */
public class MinimumHeightTrees {

    // time O(n), space O(n)
    public List<Integer> findMinHeightTreesTopologicalSort(int n, int[][] edges) {
        // 思路: 想要使得树的高度最小，即树的根结点到最深叶节点的路径最小。则这个节点一定处于最长
        // 路径的中间的一个节点或者两个节点中的任意一个。否则任取另外一个点都可以得到一条更长的根
        // 到叶的路径 - 因为非中心节点一定离一头近而另一头远。
        // 可以使用拓扑排序，每次将度为 1 的节点入列直到最后 1 或者 2 个节点。
        if (n == 1) {
            return List.of(0);
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
        return res;
    }
}
