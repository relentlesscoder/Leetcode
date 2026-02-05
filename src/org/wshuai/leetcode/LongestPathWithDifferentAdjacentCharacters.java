package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 02/03/2024.
 * #2246 https://leetcode.com/problems/longest-path-with-different-adjacent-characters/
 */
public class LongestPathWithDifferentAdjacentCharacters {

    private int res = 0;

    // time O(n), space O(n)
    public int longestPath(int[] parent, String s) {
        // 同 #0543 思路
        res = 0;
        int n = parent.length;
        // 构造邻接表
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }
        dfs(0, adj, s.toCharArray());
        return res;
    }

    private int dfs(int node, List<Integer>[] adj, char[] s) {
        int max = 0, // 最长路径
                second = 0; // 第二最长路径
        // 对每个子节点进行递归求出经过它们返回的最长路径
        for (int next : adj[node]) {
            // 注意需要先递归在判断相邻字符
            // 示例1: parent = [-1, 0, 1] s = "aab"
            //   a
            //  a
            // b
            // 如果先判断则不会递归到节点 1 则答案为 1 是错误的。
            int len = dfs(next, adj, s);
            if (s[next] == s[node]) { // 如果相邻节点相同则路径不符合要求
                continue;
            }
            if (len > max) {
                second = max;
                max = len;
            } else if (len > second) {
                second = len;
            }
        }
        // 经过当前节点的最长路径为两个最长的子节点的路径加上当前节点本身
        res = Math.max(res, 1 + max + second);
        // 返回最长子节点路径加上当前节点本身
        return max + 1;
    }
}
