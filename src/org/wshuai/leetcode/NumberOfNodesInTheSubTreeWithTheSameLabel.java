package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 08/04/2020.
 * #1519 https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/
 */
public class NumberOfNodesInTheSubTreeWithTheSameLabel {

    // time O(n), space O(n)
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        int[] res = new int[n];
        // 构造邻接表
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        dfs(0, -1, adj, labels.toCharArray(), res);
        return res;
    }

    private int[] dfs(int node, int parent, List<Integer>[] adj, char[] arr, int[] res) {
        int[] freq = new int[26]; // 表示以当前节点为根结点的子树中 26 个小写字母的数量
        for (int next : adj[node]) {
            // 树的 DFS 只需要保证下一个节点不是父节点即可
            if (next == parent) {
                continue;
            }
            // DFS 找子树中 26 个小写字母的数量
            int[] f = dfs(next, node, adj, arr, res);
            // 加到当前子树的中
            for (int i = 0; i < 26; i++) {
                freq[i] += f[i];
            }
        }
        // 更新答案
        res[node] = ++freq[arr[node] - 'a'];
        return freq;
    }
}
