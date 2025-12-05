package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/07/2025.
 * #2097 https://leetcode.com/problems/valid-arrangement-of-pairs/
 */
public class ValidArrangementOfPairs {

    // time O(V + E), space O(V + E)
    public int[][] validArrangementHierholzer(int[][] pairs) {
        Map<Integer, Deque<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();
        for (int[] pair : pairs) {
            int start = pair[0], end = pair[1];
            adj.computeIfAbsent(start, value -> new ArrayDeque<>()).offer(end);
            outDegree.put(start, outDegree.getOrDefault(start, 0) + 1);
            inDegree.put(end, inDegree.getOrDefault(end, 0) + 1);
        }
        List<Integer> path = new ArrayList<>();
        int startNode = -1;
        for (int node : outDegree.keySet()) {
            if (outDegree.get(node) == inDegree.getOrDefault(node, 0) + 1) {
                startNode = node;
                break;
            }
        }
        if (startNode == -1) {
            startNode = pairs[0][0];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(startNode);
        while (!stack.isEmpty()) {
            int currNode = stack.peek();
            if (adj.containsKey(currNode) && !adj.get(currNode).isEmpty()) {
                int nextNode = adj.get(currNode).poll();
                stack.push(nextNode);
            } else {
                path.add(stack.pop());
            }
        }
        Collections.reverse(path);
        int[][] res = new int[path.size() - 1][2];
        for (int i = 1; i < path.size(); i++) {
            res[i - 1] = new int[] {path.get(i - 1), path.get(i)};
        }
        return res;
    }

    // time O(V + E), space O(V + E)
    public int[][] validArrangementDFS(int[][] pairs) {
        // https://leetcode.com/problems/valid-arrangement-of-pairs/editorial/
        Map<Integer, Deque<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();
        for (int[] pair : pairs) {
            int start = pair[0], end = pair[1];
            adj.computeIfAbsent(start, value -> new ArrayDeque<>()).offer(end);
            outDegree.put(start, outDegree.getOrDefault(start, 0) + 1);
            inDegree.put(end, inDegree.getOrDefault(end, 0) + 1);
        }
        List<Integer> path = new ArrayList<>();
        int startNode = -1;
        // find the start node (outDegree == inDegree + 1)
        for (int node : outDegree.keySet()) {
            if (outDegree.get(node) == inDegree.getOrDefault(node, 0) + 1) {
                startNode = node;
                break;
            }
        }
        // if no such node exists, start from the first pair's first element
        if (startNode == -1) {
            startNode = pairs[0][0];
        }
        dfs(startNode, adj, path);
        Collections.reverse(path);
        int[][] res = new int[path.size() - 1][2];
        for (int i = 1; i < path.size(); i++) {
            res[i - 1] = new int[] {path.get(i - 1), path.get(i)};
        }
        return res;
    }

    private void dfs(int node, Map<Integer, Deque<Integer>> adj, List<Integer> res) {
        Deque<Integer> neighbors = adj.get(node);
        while (neighbors != null && !neighbors.isEmpty()) {
            int nextNode = neighbors.poll();
            dfs(nextNode, adj, res);
        }
        res.add(node);
    }
}
