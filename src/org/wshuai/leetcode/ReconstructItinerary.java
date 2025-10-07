package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 02/20/2017.
 * #0332 https://leetcode.com/problems/reconstruct-itinerary/
 */
public class ReconstructItinerary {

    // time O(V + E * log(E)), space O(V + E)
    public List<String> findItineraryHierholzer(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> adj = new HashMap<>();
        for (List<String> ticket : tickets) {
            String start = ticket.get(0), end = ticket.get(1);
            adj.computeIfAbsent(start, value -> new PriorityQueue<>()).offer(end);
        }
        List<String> path = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            String currNode = stack.peek();
            if (adj.containsKey(currNode) && !adj.get(currNode).isEmpty()) {
                stack.push(adj.get(currNode).poll());
            } else {
                path.add(stack.pop());
            }
        }
        Collections.reverse(path);
        return path;
    }

    // time O(V + E * log(E)), space O(V + E)
    public List<String> findItineraryDFS(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> adj = new HashMap<>();
        for (List<String> ticket : tickets) {
            String start = ticket.get(0), end = ticket.get(1);
            adj.computeIfAbsent(start, value -> new PriorityQueue<>()).offer(end);
        }
        List<String> path = new ArrayList<>();
        dfs("JFK", adj, path);
        Collections.reverse(path);
        return path;
    }

    private void dfs(String node, Map<String, PriorityQueue<String>> adj, List<String> path) {
        PriorityQueue<String> minQueue = adj.get(node);
        while (minQueue != null && !minQueue.isEmpty()) {
            String nextNode = minQueue.poll();
            dfs(nextNode, adj, path);
        }
        path.add(node);
    }
}
