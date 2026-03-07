package org.wshuai.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/15/2019.
 * #0742 https://leetcode.com/problems/closest-leaf-in-a-binary-tree/
 */
public class ClosestLeafInABinaryTree {

    private int res = 0;
    private int min = Integer.MAX_VALUE;

    // time O(n), space O(n)
    public int findClosestLeafDFS(TreeNode root, int k) {
        res = 0;
        min = Integer.MAX_VALUE;
        Map<Integer, Integer> distMap = new HashMap<>();
        findTarget(root, k, distMap);
        findLeafNodes(root, 0, distMap);
        return res;
    }

    private void findLeafNodes(TreeNode root, int dist, Map<Integer, Integer> distMap) {
        if (root == null) {
            return;
        }
        if (distMap.containsKey(root.val)) {
            dist = distMap.get(root.val);
        }
        if (root.left == null && root.right == null && dist < min) {
            min = dist;
            res = root.val;
        }
        findLeafNodes(root.left, dist + 1, distMap);
        findLeafNodes(root.right, dist + 1, distMap);
    }

    private int findTarget(TreeNode root, int k, Map<Integer, Integer> distMap) {
        // 找到 target 节点并且用哈希表记录它到根节点路径上各节点的从底向上距离
        if (root == null) {
            return -1;
        }
        if (root.val == k) {
            distMap.put(k, 0);
            return 0;
        }
        int left = findTarget(root.left, k, distMap);
        if (left >= 0) {
            distMap.put(root.val, left + 1);
            return left + 1;
        }
        int right = findTarget(root.right, k, distMap);
        if (right >= 0) {
            distMap.put(root.val, right + 1);
            return right + 1;
        }
        return -1;
    }

    // time O(n), space O(n)
    public int findClosestLeafBFS(TreeNode root, int k) {
        int res = -1, dist = 0, min = Integer.MAX_VALUE;
        // 邻接矩阵，adj[i] 的三个值分别表示父节点，左子节点和右子节点
        int[][] adj = new int[1001][3];
        int[] visited = new int[1001];
        Arrays.setAll(adj, i -> new int[]{-1, -1, -1});
        // 使用 DFS 来构建邻接矩阵
        buildGraph(root, null, adj);
        // BFS 来找到与 k 距离最小的叶子节点
        List<Integer> queue = new ArrayList<>();
        queue.add(k);
        visited[k] = 1;
        while (!queue.isEmpty()) {
            List<Integer> next = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                int curr = queue.get(i);
                // 叶子节点且与 k 距离更小
                if (adj[curr][1] == -1 && adj[curr][2] == -1 && dist < min) {
                    min = dist;
                    res = curr;
                }
                for (int x : adj[curr]) {
                    if (x != -1 && visited[x] == 0) {
                        visited[x] = 1;
                        next.add(x);
                    }
                }
            }
            dist++;
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
