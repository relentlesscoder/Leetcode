package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 01/01/2024.
 * #2641 https://leetcode.com/problems/cousins-in-binary-tree-ii/
 */
public class CousinsInBinaryTreeII {

    // time O(n), space O(n)
    public TreeNode replaceValueInTreeBFS(TreeNode root) {
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            // 先求层节点和
            int sum = 0;
            for (TreeNode node : queue) {
                sum += node.left == null ? 0 : node.left.val;
                sum += node.right == null ? 0 : node.right.val;
            }
            // 再将每个节点值用堂兄弟和替代
            for (TreeNode node : queue) {
                // 计算兄弟和
                int childSum = node.left == null ? 0 : node.left.val;
                childSum += node.right == null ? 0 : node.right.val;
                // 节点的堂兄弟和就等于该层节点和减去兄弟和
                if (node.left != null) {
                    node.left.val = sum - childSum;
                    next.add(node.left);
                }
                if (node.right != null) {
                    node.right.val = sum - childSum;
                    next.add(node.right);
                }
            }
            queue = next;
        }
        root.val = 0;
        return root;
    }

    // time O(n), space O(n)
    public TreeNode replaceValueInTreeBFSAndDFS(TreeNode root) {
        // BFS 求每一层的和
        List<Integer> levelSum = new ArrayList<>();
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
            levelSum.add(sum);
        }
        // dfs 将每个节点设为堂兄弟和
        root.val = 0;
        dfs(root, 0, levelSum);
        return root;
    }

    // time O(n), space O(n)
    public TreeNode replaceValueInTreeDoubleDFS(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        // 第一遍 dfs 求每一层的和
        getRowSum(root, 0, nums);
        root.val = 0;
        // 第二遍 dfs 将每个节点设为堂兄弟和
        dfs(root, 0, nums);
        return root;
    }

    private void getRowSum(TreeNode root, int depth, List<Integer> nums) {
        if (root == null) {
            return;
        }
        if (nums.size() == depth) {
            nums.add(root.val);
        } else {
            nums.set(depth, root.val + nums.get(depth));
        }
        getRowSum(root.left, depth + 1, nums);
        getRowSum(root.right, depth + 1, nums);
    }

    private void dfs(TreeNode root, int level, List<Integer> levelSum) {
        // 先计算兄弟和
        int childSum = root.left == null ? 0 : root.left.val;
        childSum += root.right == null ? 0 : root.right.val;
        // 节点的堂兄弟和就等于该层节点和减去兄弟和
        if (root.left != null) {
            root.left.val = levelSum.get(level + 1) - childSum;
            dfs(root.left, level + 1, levelSum);
        }
        if (root.right != null) {
            root.right.val = levelSum.get(level + 1) - childSum;
            dfs(root.right, level + 1, levelSum);
        }
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
