package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 09/23/2019.
 * #0863 https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 */
public class AllNodesDistanceKInBinaryTree {

    // time O(n), space O(n)
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> distMap = new HashMap<>();
        findTarget(root, target, distMap);
        findLeafNodes(root, 0, k, distMap, res);
        return res;
    }

    private void findLeafNodes(TreeNode root, int dist, int k,
                               Map<Integer, Integer> distMap, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (distMap.containsKey(root.val)) {
            dist = distMap.get(root.val);
        }
        if (dist == k) {
            res.add(root.val);
        }
        findLeafNodes(root.left, dist + 1, k, distMap, res);
        findLeafNodes(root.right, dist + 1, k, distMap, res);
    }

    private int findTarget(TreeNode root, TreeNode target, Map<Integer, Integer> distMap) {
        // 找到 target 节点并且用哈希表记录它到根节点路径上各节点的从底向上距离
        if (root == null) {
            return -1;
        }
        if (root.val == target.val) {
            distMap.put(target.val, 0);
            return 0;
        }
        // 最多只有一边会返回非负的结果
        int left = findTarget(root.left, target, distMap);
        if (left >= 0) {
            distMap.put(root.val, left + 1);
            return left + 1;
        }
        int right = findTarget(root.right, target, distMap);
        if (right >= 0) {
            distMap.put(root.val, right + 1);
            return right + 1;
        }
        return -1;
    }

    // time O(n), space O(n)
    public List<Integer> distanceKBFS(TreeNode root, TreeNode target, int k) {
        if (k == 0) {
            return List.of(target.val);
        }
        List<Integer> res = new ArrayList<>();
        int dist = 0;
        // 邻接矩阵，adj[i] 的三个值分别表示父节点，左子节点和右子节点
        int[][] adj = new int[501][3];
        int[] visited = new int[501];
        Arrays.setAll(adj, i -> new int[]{-1, -1, -1});
        // 使用 DFS 来构建邻接矩阵
        buildGraph(root, null, adj);
        // BFS 来找到距离为 k 的节点
        List<Integer> queue = new ArrayList<>();
        queue.add(target.val);
        visited[target.val] = 1;
        while (!queue.isEmpty()) {
            List<Integer> next = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                int curr = queue.get(i);
                for (int x : adj[curr]) {
                    if (x != -1 && visited[x] == 0) {
                        visited[x] = 1;
                        next.add(x);
                    }
                }
            }
            if (dist++ == k - 1) {
                res = next;
                break;
            }
            queue = next;
        }
        return res;
    }

    private void buildGraph(TreeNode root, TreeNode parent, int[][] adj) {
        if (root == null) {
            return;
        }
        if (parent != null) {
            adj[root.val][0] = parent.val;
        }
        if (root.left != null) {
            adj[root.val][1] = root.left.val;
        }
        if (root.right != null) {
            adj[root.val][2] = root.right.val;
        }
        buildGraph(root.left, root, adj);
        buildGraph(root.right, root, adj);
    }

    /**
     * Definition for a binary tree node.
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
