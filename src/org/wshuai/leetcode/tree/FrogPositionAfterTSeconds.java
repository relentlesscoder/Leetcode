package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 03/09/2020.
 * #1377 https://leetcode.com/problems/frog-position-after-t-seconds/
 */
public class FrogPositionAfterTSeconds {

    private double res = 0.0;

    // time O(n), space O(n)
    public double frogPosition(int n, int[][] edges, int t, int target) {
        res = 0.0;
        List<Integer>[] adj = new ArrayList[n + 1];
        Arrays.setAll(adj, i -> new ArrayList<>());
        adj[1].add(0); // 避免单独判断根结点的小技巧
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
		// 因为选择每条路都是同样的几率所以可以计算路径上的乘积 prod 然后将答案设为 1 / prod。
        dfs(1, 0, 1L, t, adj, target);
        return res;
    }

    private boolean dfs(int node, int parent, long prod, int time,
                        List<Integer>[] adj, int target) {
		// 找到目标节点的条件:
		//   1. 当前节点为 target
		//   2a. 时间为 0
		//   2b. 时间不为 0 但是已经无路可走
        if (node == target && (time == 0 || adj[node].size() == 1)) {
            res = 1.0 / prod; // 计算结果
            return true;
        }
		// 不满足条件的情况:
		//   1. 找到 target 但时间没用完且有路可走
		//   2. 没找到 target 但是时间已经用完
        if (node == target || time == 0) {
            return false;
        }
        for (int next : adj[node]) {
			// 树的 DFS 只需要保证下一个节点不是父节点即可
            if (next != parent && dfs(next, node, prod * (adj[node].size() - 1),
                    time - 1, adj, target)) {
                return true;
            }
        }
        return false;
    }
}
