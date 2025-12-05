package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/15/2016.
 * #0314 https://leetcode.com/problems/binary-tree-vertical-order-traversal/
 */
public class BinaryTreeVerticalOrderTraversal {

    // time O(n), space O(n)
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        int[] columnRange = new int[]{0, 0};
        dfs(root, columnRange, 0);
        for (int i = columnRange[0]; i <= columnRange[1]; i++) {
            res.add(new ArrayList<>());
        }
        Deque<TreeNode> nodes = new ArrayDeque<>();
        Deque<Integer> columns = new ArrayDeque<>();
        nodes.offer(root);
        columns.offer(0);
        while (!nodes.isEmpty()) {
            TreeNode curr = nodes.poll();
            int col = columns.poll();
            res.get(col - columnRange[0]).add(curr.val);
            if (curr.left != null) {
                nodes.offer(curr.left);
                columns.offer(col - 1);
            }
            if (curr.right != null) {
                nodes.offer(curr.right);
                columns.offer(col + 1);
            }
        }
        return res;
    }

    private void dfs(TreeNode node, int[] columnRange, int col) {
        if (node == null) {
            return;
        }

        columnRange[0] = Math.min(columnRange[0], col);
        columnRange[1] = Math.max(columnRange[1], col);

        dfs(node.left, columnRange, col - 1);
        dfs(node.right, columnRange, col + 1);
    }

    // time O(n), space O(n)
    public List<List<Integer>> verticalOrderHashMap(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        int minCol = 0, maxCol = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Deque<TreeNode> nodes = new ArrayDeque<>();
        Deque<Integer> columns = new ArrayDeque<>();
        nodes.offer(root);
        columns.offer(0);
        while (!nodes.isEmpty()) {
            TreeNode curr = nodes.poll();
            int col = columns.poll();
            map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(curr.val);
            if (curr.left != null) {
                minCol = Math.min(minCol, col - 1);
                nodes.offer(curr.left);
                columns.offer(col - 1);
            }
            if (curr.right != null) {
                maxCol = Math.max(maxCol, col + 1);
                nodes.offer(curr.right);
                columns.offer(col + 1);
            }
        }
        for (int i = minCol; i <= maxCol; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}
