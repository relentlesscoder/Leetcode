package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 01/26/2026.
 * #3157 https://leetcode.com/problems/find-the-level-of-tree-with-minimum-sum/
 */
public class FindTheLevelOfTreeWithMinimumSum {

    // time O(n), space O(n)
    public int minimumLevel(TreeNode root) {
        int res = -1, depth = 1;
        long min = Long.MAX_VALUE;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;
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
            if (sum < min) {
                min = sum;
                res = depth;
            }
            depth++;
        }
        return res;
    }

    // time O(n), space O(n)
    public int minimumLevelDFS(TreeNode root) {
        int res = -1;
        long min = Long.MAX_VALUE;
        List<Long> nums = new ArrayList<>();
        dfs(root, 0, nums);
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) < min) {
                min = nums.get(i);
                res = i + 1;
            }
        }
        return res;
    }

    private void dfs(TreeNode root, int depth, List<Long> nums) {
        if (root == null) {
            return;
        }
        if (nums.size() == depth) {
            nums.add((long) root.val);
        } else {
            nums.set(depth, nums.get(depth) + root.val);
        }
        dfs(root.left, depth + 1, nums);
        dfs(root.right, depth + 1, nums);
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
