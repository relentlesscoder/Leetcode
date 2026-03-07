package org.wshuai.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Wei on 01/01/2026.
 * #2791 https://leetcode.com/problems/count-paths-that-can-form-a-palindrome-in-a-tree/
 */
public class CountPathsThatCanFormAPalindromeInATree {

    // time O(n), space O(n)
    public long countPalindromePaths(List<Integer> parent, String s) {
        // #1542相似题, 对树做 DFS 然后根据前缀奇偶性异或和来统计回文的数量。
        // DFS 的遍历顺序保证处理到一个点时，另一侧到达该点的路径的前缀和已经
        // 在哈希表中。
        // 示例1: parent = [-1,0,0,1,1,2], s = "acaabc"
        // 哈希表的遍历顺序为 [0,1,3,4,2,5] , 当处理到点 2 时另一侧路径 [3,1,0]
        //  和 [4,1,0] 前缀和已经在哈希表中。
        int n = parent.size();
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            adj[parent.get(i)].add(i);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return dfs(0, 0, adj, s.toCharArray(), map);
    }

    private long dfs(int i, int prev, List<Integer>[] adj, char[] s, HashMap<Integer, Integer> map) {
        long res = 0;
        for (int x : adj[i]) {
            int mask = prev ^ (1 << (s[x] - 'a'));
            // 路径中的节点数的字符的数量全是偶数的情况
            res += map.getOrDefault(mask, 0);
            // 允许一个字符的数量为奇数
            for (int j = 0; j < 26; j++) {
                int curr = mask ^ (1 << j);
                res += map.getOrDefault(curr, 0);
            }
            // 更新哈希表
            map.merge(mask, 1, Integer::sum);
            res += dfs(x, mask, adj, s, map);
        }
        return res;
    }
}
