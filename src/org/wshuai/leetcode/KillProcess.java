package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 09/30/2019.
 * #0582 https://leetcode.com/problems/kill-process/
 */
public class KillProcess {

    // time O(n), space O(n)
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> res = new ArrayList<>();
        int n = pid.size(), parent = 0;
        // 创建邻接列表
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (ppid.get(i) == 0) {
                continue;
            }
            if (kill == pid.get(i)) {
                parent = ppid.get(i);
            }
            adj.computeIfAbsent(ppid.get(i), k -> new ArrayList<>()).add(pid.get(i));
        }
        // 从 kill 节点开始 DFS 找到所有可以杀死的进程
        dfs(kill, parent, adj, res);
        return res;
    }

    private void dfs(int node, int parent, Map<Integer, List<Integer>> adj, List<Integer> res) {
        res.add(node);
        for (int next : adj.getOrDefault(node, new ArrayList<>())) {
            // 树的 DFS 只需要保证不回到父节点即可，无需额外的数组存遍历状态
            if (next == parent) {
                continue;
            }
            dfs(next, node, adj, res);
        }
    }
}
