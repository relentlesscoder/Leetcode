package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 08/30/2019.
 * #1161 https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
 */
public class MaximumLevelSumOfABinaryTree {

    // time O(n), space O(n)
    public int maxLevelSumRecursive(TreeNode root) {
        // #0515 相同思路
        int res = 0;
        List<Integer> vals = new ArrayList<>();
        dfs(root, 0, vals);
        // 找到最大层
        for (int i = 0, max = Integer.MIN_VALUE; i < vals.size(); i++) {
            if (vals.get(i) > max) {
                max = vals.get(i);
                res = i + 1;
            }
        }
        return res;
    }

    private void dfs(TreeNode root, int depth, List<Integer> vals) {
        if (root == null) {
            return;
        }
        if (vals.size() == depth) { // 将每一层第一个节点的值加入答案队列
            vals.add(root.val);
        } else { // 将节点加入到它所在层的节点和中
            vals.set(depth, root.val + vals.get(depth));
        }
        dfs(root.left, depth + 1, vals);
        dfs(root.right, depth + 1, vals);
    }

    // time O(n), space O(n)
    public int maxLevelSumBFS(TreeNode root) {
        int res = 0, max = Integer.MIN_VALUE, depth = 1;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(), sum = 0;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (sum > max) {
                max = sum;
                res = depth;
            }
            depth++;
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
