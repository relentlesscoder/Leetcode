package org.wshuai.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 11/15/2016.
 * #0314 https://leetcode.com/problems/binary-tree-vertical-order-traversal/
 */
public class BinaryTreeVerticalOrderTraversal {

    // time O(n), space O(n)
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // 用 BFS 遍历二叉树，BFS 遍历顺序为逐层遍历且层内从左向右。
        int min = Integer.MAX_VALUE;
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<Integer>> colMap = new HashMap<>();
        Deque<TreeNode> nodeQueue = new ArrayDeque<>();
        Deque<Integer> columnQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        columnQueue.offer(0);
        while (!nodeQueue.isEmpty()) {
            TreeNode curr = nodeQueue.poll();
            int col = columnQueue.poll();
            colMap.computeIfAbsent(col, k -> new ArrayList<>()).add(curr.val);
            min = Math.min(min, col);
            if (curr.left != null) {
                nodeQueue.offer(curr.left);
                columnQueue.offer(col - 1);
            }
            if (curr.right != null) {
                nodeQueue.offer(curr.right);
                columnQueue.offer(col + 1);
            }
        }
        for (int i = min; i < min + colMap.size(); i++) {
            res.add(colMap.get(i));
        }
        return res;
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
