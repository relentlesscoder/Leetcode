package org.wshuai.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 02/04/2026.
 * #3331 https://leetcode.com/problems/find-subtree-sizes-after-changes/
 */
public class FindSubtreeSizesAfterChanges {

    // time O(n), space O(n)
    public int[] findSubtreeSizes(int[] parent, String s) {
        // 思路: 最直接思路是跑两次 DFS ，第一次按照题目要求修改树的结构然后第二次计算每个子树的大小。
        // 更优化的解法是只用一遍 DFS ，对每个节点为根结点的子树，在计算子树节点数量时不统计那些从根
        // 结点出发到当前节点的路径上已出现过的子节点的子树的节点数量而是把它加到上一次出现的相同节点上。
        // 如何找到上一次出现的相同节点呢？可以对每一个字符可以维护一个栈，栈顶就是该字符上次出现的节点。
        // 利用回溯来维护这些栈使得栈表示当前路径上的节点。
        int n = parent.length;
        int[] res = new int[n];
        // 构造邻接表
        List<Integer>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 1; i < parent.length; i++) {
            adj[parent[i]].add(i);
        }
        Deque<Integer>[] stacks = new ArrayDeque[26];
        Arrays.setAll(stacks, i -> new ArrayDeque<>());
        dfs(0, -1, adj, s.toCharArray(), stacks, res);
        return res;
    }

    private int dfs(int node, int parent, List<Integer>[] adj,
                    char[] s, Deque<Integer>[] stacks, int[] res) {
        int count = 1; // 当前节点为根结点子树的节点数量
        stacks[s[node] - 'a'].push(node); // 将当前节点加入对应的栈
        for (int next : adj[node]) {
            if (next == parent) {
                continue;
            }
            int size = dfs(next, node, adj, s, stacks, res);
            // 如果路径上存在相同的字符
            if (!stacks[s[next] - 'a'].isEmpty()) {
                int pred = stacks[s[next] - 'a'].peek();
                // 将子树节点数量加入到上一个相同字符节点数量中
                res[pred] += size;
            } else { // 否则不用改变当前树的父节点，直接统计子树节点数量
                count += size;
            }
        }
        stacks[s[node] - 'a'].pop(); // 回溯完打扫战场: 将当前节点从对应的栈弹出
        // 返回时记得将 res[node] (子树中相同字符的子树节点数量) 也加入答案
        return res[node] = count + res[node];
    }
}
