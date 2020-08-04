package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 08/04/2020.
 * #1519 https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/
 */
public class NumberOfNodesInTheSubTreeWithTheSameLabel {

    // time O(n), space O(n)
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for(int[] e : edges){
            tree.putIfAbsent(e[0], new ArrayList<>());
            tree.putIfAbsent(e[1], new ArrayList<>());
            tree.get(e[0]).add(e[1]);
            tree.get(e[1]).add(e[0]);
        }
        int[] res = new int[n];
        dfs(0, tree, res, labels.toCharArray(), new boolean[n]);
        return res;
    }

    private int[] dfs(int cur, Map<Integer, List<Integer>> tree, int[] res, char[] lables, boolean[] visited){
        int[] count = new int[26];
        visited[cur] = true;
        for(int next : tree.get(cur)){
            if(visited[next]){
                continue;
            }
            int[] temp = dfs(next, tree, res, lables, visited);
            for(int i = 0; i < 26; i++){
                count[i] += temp[i];
            }
        }
        int index = lables[cur] - 'a';
        count[index]++;
        res[cur] = count[index];
        return count;
    }
}
