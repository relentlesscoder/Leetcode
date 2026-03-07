package org.wshuai.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/06/2025.
 * #2467 https://leetcode.com/problems/most-profitable-path-in-a-tree/
 */
public class MostProfitablePathInATree {

    private int maxIncome = Integer.MIN_VALUE;

    // time O(n), space O(n)
    public int mostProfitablePathSingleDFS(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        int[] timeToBob = new int[n]; // timeToBob[i] 是从当前节点 i 到 bob 所需要的时间
        ArrayList<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, index -> new ArrayList<>());
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        return findPaths(0, 0, 0, bob, amount, timeToBob, adj);
    }

    private int findPaths(
            int node,
            int parentNode,
            int time,
            int bob,
            int[] amount,
            int[] timeToBob,
            ArrayList<Integer>[] adj
    ) {
        int n = amount.length;
        int income = 0, maxChild = Integer.MIN_VALUE;
        if (node == bob) {
            timeToBob[node] = 0;
        } else {
            timeToBob[node] = n;
        }
        for (int nextNode : adj[node]) {
            if (nextNode != parentNode) {
                // 计算当前节点的子节点到叶子节点的最大得分
                maxChild = Math.max(
                        maxChild,
                        findPaths(nextNode, node, time + 1, bob, amount, timeToBob, adj)
                );
                // 更新节点到 bob 的时间
                timeToBob[node] = Math.min(
                        timeToBob[node],
                        timeToBob[nextNode] + 1
                );
            }
        }
        // 如果比 bob 先到则 alice 需要得全部的分或者扣全部的分
        if (timeToBob[node] > time) {
            income += amount[node];
        } else if (timeToBob[node] == time) { // 如果到达时间相同则得分或者扣分减半
            income += amount[node] / 2;
        }
        // 当前节点的得分加上子节点的最大得分即为答案
        return (maxChild == Integer.MIN_VALUE)
                ? income
                : income + maxChild;
    }

    // time O(n), space O(n)
    public int mostProfitablePathDoubleDFS(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        // 构造邻接表
        ArrayList<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, index -> new ArrayList<>());
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        // 哈希表存 bob 的路径节点与时间的映射
        Map<Integer, Integer> bobPath = new HashMap<>();
        // DFS 寻找 bob 的路径
        findBobPath(bob, -1, 0, bobPath, adj);
        findAlicePath(0, -1, 0, 0, amount, bobPath, adj);
        return maxIncome;
    }

    private void findAlicePath(int node, int parent, int time, int income, int[] amount,
                               Map<Integer, Integer> bobPath, ArrayList<Integer>[] adj) {
        // 如果当前节点不在 bob 的路径上或者到达时间比 bob 到达时间早，则 alice 需要得全部的分或者扣全部的分
        if (!bobPath.containsKey(node) || time < bobPath.get(node)) {
            income += amount[node];
        } else if (time == bobPath.get(node)) { // 如果到达时间相同则得分或者扣分减半
            income += amount[node] / 2;
        } // 比 bob 晚到，既不得分也不扣分
        if (adj[node].size() == 1 && node != 0) { // 到达叶子节点
            maxIncome = Math.max(maxIncome, income); // 更新最大得分
            return;
        }
        for (int nextNode : adj[node]) {
            if (nextNode != parent) {
                findAlicePath(nextNode, node, time + 1, income, amount, bobPath, adj);
            }
        }
    }

    private boolean findBobPath(int node, int parent, int time,
                                Map<Integer, Integer> bobPath, ArrayList<Integer>[] adj) {
        // 用回溯来找 bob 到节点 0 的路径
        bobPath.put(node, time);
        if (node == 0) {
            return true;
        }
        for (int nextNode : adj[node]) {
            if (nextNode != parent
                    && findBobPath(nextNode, node, time + 1, bobPath, adj)) {
                return true;
            }
        }
        // 如果没找到则打扫战场
        bobPath.remove(node);
        return false;
    }

    // time O(n), space O(n)
    public int mostProfitablePathBFSAndDFS(int[][] edges, int bob, int[] amount) {
        int res = Integer.MIN_VALUE, n = amount.length;
        // 构造邻接表
        ArrayList<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, index -> new ArrayList<>());
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        // 哈希表存 bob 的路径节点与时间的映射
        Map<Integer, Integer> bobPath = new HashMap<>();
        // DFS 寻找 bob 的路径
        findBobPath(bob, -1, 0, bobPath, adj);
        boolean[] visited = new boolean[n];
        // BFS 寻找最大路径
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0], time = curr[1], income = curr[2];
            visited[node] = true;
            // 如果当前节点不在 bob 的路径上或者到达时间比 bob 到达时间早，则 alice 需要得全部的分或者扣全部的分
            if (!bobPath.containsKey(node) || time < bobPath.get(node)) {
                income += amount[node];
            } else if (time == bobPath.get(node)) { // 如果到达时间相同则得分或者扣分减半
                income += amount[node] / 2;
            } // 如果比 bob 晚到既不得分也不扣分
            if (adj[node].size() == 1 && node != 0) { // 到达叶子节点
                res = Math.max(res, income); // 更新最大得分
            }
            for (int nextNode : adj[node]) {
                if (!visited[nextNode]) {
                    queue.offer(new int[]{nextNode, time + 1, income});
                }
            }
        }
        return res;
    }
}
