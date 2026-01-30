package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 09/23/2019.
 * #0987 https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 */
public class VerticalOrderTraversalOfABinaryTree {

    private int min = Integer.MAX_VALUE;

    // time O(n * log(n)), space O(n)
    public List<List<Integer>> verticalTraversalDFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<int[]>> map = new HashMap<>();
        dfs(root, 0, 0, map);
        for (int i = min; map.containsKey(i); i++) { // 从小到大遍历列的值
            // 拿到该列对应的值和行的集合
            List<int[]> curr = map.get(i);
            // 按照行数由小到大排序，行数一样则按照值由小到大排序
            Collections.sort(curr, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
            List<Integer> nums = new ArrayList<>();
            for (int j = 0; j < curr.size(); j++) {
                nums.add(curr.get(j)[0]);
            }
            res.add(nums);
        }
        return res;
    }

    private void dfs(TreeNode root, int row, int col, Map<Integer, List<int[]>> map) {
        if (root == null) {
            return;
        }
        min = Math.min(min, col);
        map.computeIfAbsent(col, k -> new ArrayList<>()).add(new int[]{root.val, row});
        dfs(root.left, row + 1, col - 1, map);
        dfs(root.right, row + 1, col + 1, map);
    }

    // time O(n * log(n)), space O(n)
    public List<List<Integer>> verticalTraversalBFS(TreeNode root) {
        // 瓶颈为排序
        List<List<Integer>> res = new ArrayList<>();
        int min = Integer.MAX_VALUE; // 表示列的最小值
        Map<Integer, List<int[]>> map = new HashMap<>(); // 列到值和行的哈希表
        Deque<TreeNode> nodeQueue = new ArrayDeque<>(); // 节点队列
        Deque<int[]> posQueue = new ArrayDeque<>(); // 位置队列
        nodeQueue.offer(root);
        posQueue.offer(new int[]{0, 0});
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            while (size-- > 0) {
                TreeNode node = nodeQueue.poll();
                int[] pos = posQueue.poll();
                min = Math.min(min, pos[1]);
                map.computeIfAbsent(pos[1], k -> new ArrayList<>()).add(new int[]{node.val, pos[0]});
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    posQueue.offer(new int[]{pos[0] + 1, pos[1] - 1});
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    posQueue.offer(new int[]{pos[0] + 1, pos[1] + 1});
                }
            }
        }
        for (int i = min; map.containsKey(i); i++) { // 从小到大遍历列的值
            // 拿到该列对应的值和行的集合
            List<int[]> curr = map.get(i);
            // 按照行数由小到大排序，行数一样则按照值由小到大排序
            Collections.sort(curr, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
            List<Integer> nums = new ArrayList<>();
            for (int j = 0; j < curr.size(); j++) {
                nums.add(curr.get(j)[0]);
            }
            res.add(nums);
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
