package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 05/11/2020.
 * #1443 https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
 */
public class MinimumTimeToCollectAllApplesInATree {

    // time O(n), space O(n)
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for(int[] e : edges){
            adj.putIfAbsent(e[0], new ArrayList<>());
            adj.putIfAbsent(e[1], new ArrayList<>());
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        return dfs(0, adj, new HashSet<Integer>(), hasApple);
    }

    private int dfs(int cur, Map<Integer, List<Integer>> adj, Set<Integer> visited, List<Boolean> hasApple){
        visited.add(cur);
        int res = 0;
        for(int next : adj.get(cur)){
            if(visited.contains(next)){
                continue;
            }
            res += dfs(next, adj, visited, hasApple);
        }
        // if there are apples in subtree or current node
        // and current node is not root
        if ((res > 0 || hasApple.get(cur)) && cur != 0){
            // each edge needs to be counted twice -
            // edge that comes in and goes out
            res += 2;
        }
        return res;
    }
}
