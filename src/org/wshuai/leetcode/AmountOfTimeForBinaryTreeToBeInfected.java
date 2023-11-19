package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/08/2023.
 * #2385 https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/
 */
public class AmountOfTimeForBinaryTreeToBeInfected {

    // time O(n), space O(n)
    public int amountOfTimeBFS(TreeNode root, int start) {
        int res = 0;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(root, null, graph);
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                for (int next : graph.getOrDefault(curr, new ArrayList<>())) {
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
            res++;
        }
        return res - 1;
    }

    // time O(n), space O(n)
    public int amountOfTimeDFS(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(root, null, graph);
        Set<Integer> visited = new HashSet<>();
        return dfs(start, 0, 0, visited, graph);
    }

    private int dfs(int node, int max, int current, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        if (visited.add(node)) {
            max = Math.max(current, max);
            for (int next : graph.getOrDefault(node, new ArrayList<>())) {
                max = Math.max(max, dfs(next, max, current + 1, visited, graph));
            }
        }
        return max;
    }

    private void buildGraph(TreeNode node, TreeNode parent, Map<Integer, List<Integer>> graph) {
        if (node == null) {
            return;
        }
        if (parent != null) {
            graph.computeIfAbsent(parent.val, val -> new ArrayList<>()).add(node.val);
            graph.computeIfAbsent(node.val, val -> new ArrayList<>()).add(parent.val);
        }
        buildGraph(node.left, node, graph);
        buildGraph(node.right, node, graph);
    }
}
