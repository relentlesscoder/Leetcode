package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 02/03/2024.
 * #2246 https://leetcode.com/problems/longest-path-with-different-adjacent-characters/
 */
public class LongestPathWithDifferentAdjacentCharacters {

    private int max = 1;

    // time O(n), space O(n)
    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }
        dfs(0, adj, s.toCharArray());
        return max;
    }

    private int dfs(int curr, List<Integer>[] adj, char[] chars) {
        int[] top = new int[]{0, 0};
        for (int i = 0; i < adj[curr].size(); i++) {
            int next = adj[curr].get(i);
            int len = dfs(next, adj, chars); // Need to do the dfs before checking equality for input like [-1, 0, 1], "aab"
            if (chars[curr] == chars[next]) {
                continue;
            }
            if (len > top[0]) {
                top[1] = top[0];
                top[0] = len;
            } else if (len > top[1]) {
                top[1] = len;
            }
        }
        max = Math.max(max, 1 + top[0] + top[1]);
        return 1 + top[0];
    }
}
