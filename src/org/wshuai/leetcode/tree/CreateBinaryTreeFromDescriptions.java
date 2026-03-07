package org.wshuai.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 11/07/2023.
 * #2196 https://leetcode.com/problems/create-binary-tree-from-descriptions/
 */
public class CreateBinaryTreeFromDescriptions {

    // time O(n), space O(n)
    public TreeNode createBinaryTreeTopologicalSort(int[][] descriptions) {
        // 父节点值到子节点值的哈希表
        Map<Integer, int[]> adj = new HashMap<>();
        // 子节点集
        Set<Integer> nodes = new HashSet<>();
        for (int[] d : descriptions) {
            // 存父节点
            int[] child = adj.computeIfAbsent(d[0], k -> new int[]{0, 0});
            // 根据 isLeft 的值设置父亲的子节点
            if (d[2] == 1) {
                child[0] = d[1];
            } else {
                child[1] = d[1];
            }
            // 将子节点加入子节点集
            nodes.add(d[1]);
        }
        // 类似拓扑排序但是因为是二叉树所以不需要存入度数组
        Deque<TreeNode> queue = new ArrayDeque<>();
        for (int key : adj.keySet()) {
            // 找到根结点
            if (!nodes.contains(key)) {
                queue.offer(new TreeNode(key));
                break;
            }
        }
        TreeNode root = queue.peek();
        // 拓扑排序
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (!adj.containsKey(curr.val)) {
                continue;
            }
            // 因为是二叉树，所以除了根结点之外所有的节点的入度都是 1 ，意味着当父节点
            // 被处理后两个子节点的入度都变成 0 - 都可以被入列。
            int[] child = adj.get(curr.val);
            if (child[0] > 0) {
                curr.left = new TreeNode(child[0]);
                queue.offer(curr.left);
            }
            if (child[1] > 0) {
                curr.right = new TreeNode(child[1]);
                queue.offer(curr.right);
            }
        }
        return root;
    }

    // time O(n), space O(n)
    public TreeNode createBinaryTreeHashMap(int[][] descriptions) {
        TreeNode root = null;
        // 值到节点的哈希表
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        // 所有存在父结点的节点集
        Set<Integer> children = new HashSet<>();
        for (int[] d : descriptions) {
            // 存父节点
            TreeNode parent = nodeMap.computeIfAbsent(d[0], k -> new TreeNode(d[0]));
            // 存子节点
            TreeNode child = nodeMap.computeIfAbsent(d[1], k -> new TreeNode(d[1]));
            // 根据 isLeft 的值设置父亲的子节点
            if (d[2] == 1) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            // 将子节点加入子节点集
            children.add(d[1]);
        }
        for (int key : nodeMap.keySet()) {
            // 如果 key 在子节点集中找不到，说明是根结点直接返回
            if (!children.contains(key)) {
                root = nodeMap.get(key);
                break;
            }
        }
        return root;
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
