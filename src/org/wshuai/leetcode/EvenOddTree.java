package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/04/2020.
 * #1609 https://leetcode.com/problems/even-odd-tree/
 */
public class EvenOddTree {

    private boolean res = true;

    public boolean isEvenOddTreeDFS(TreeNode root) {
        res = true;
        List<Integer> level = new ArrayList<>();
        dfs(root, 0, level);
        return res;
    }

    private void dfs(TreeNode root, int depth, List<Integer> level) {
        if (root == null || !res) {
            return;
        }
        if (root.val % 2 == depth % 2) { // 判断奇偶性是否与层号相反
            res = false;
            return;
        }
        if (level.size() == depth) { // 将每一层第一个节点的值加入答案队列
            level.add(root.val);
        } else { // 判断是否严格递增或递减
            if (depth % 2 == 0 && root.val <= level.get(depth)) {
                res = false;
                return;
            }
            if (depth % 2 == 1 && root.val >= level.get(depth)) {
                res = false;
                return;
            }
            level.set(depth, root.val);
        }
        dfs(root.left, depth + 1, level);
        dfs(root.right, depth + 1, level);
    }

    // time O(n), space O(n)
    public boolean isEvenOddTreeBFS(TreeNode root) {
        int depth = 0;
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                TreeNode curr = queue.get(i);
                if (curr.val % 2 == depth) { // 判断奇偶性是否与层号相反
                    return false;
                }
                // 判断是否严格递增或递减
                if (i > 0 && ((depth == 1 && curr.val >= queue.get(i - 1).val)
                        || (depth == 0 && curr.val <= queue.get(i - 1).val))) {
                    return false;
                }
                if (curr.left != null) {
                    next.add(curr.left);
                }
                if (curr.right != null) {
                    next.add(curr.right);
                }
            }
            queue = next;
            depth ^= 1;
        }
        return true;
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
