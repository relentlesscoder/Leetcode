package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 12/08/2023.
 * #2415 https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/
 */
public class ReverseOddLevelsOfBinaryTree {

    // time O(n), space O(log(n))
    public TreeNode reverseOddLevels(TreeNode root) {
        dfs(root.left, root.right, 1);
        return root;
    }

    private void dfs(TreeNode node1, TreeNode node2, int level) {
        if (node1 == null || node2 == null) {
            return;
        }
        if (level % 2 == 1) {
            int temp = node2.val;
            node2.val = node1.val;
            node1.val = temp;
        }
        dfs(node1.left, node2.right, level + 1);
        dfs(node1.right, node2.left, level + 1);
    }

    // time O(n), space O(n)
    public TreeNode reverseOddLevelsBFS(TreeNode root) {
        if (root == null) {
            return root;
        }
        int level = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            level++;
            if (level % 2 == 1 && !queue.isEmpty()) {
                int[] nums = new int[queue.size()];
                int i = 0, j = queue.size() - 1;
                for (TreeNode node : queue) {
                    nums[i++] = node.val;
                }
                for (TreeNode node : queue) {
                    node.val = nums[j--];
                }
            }
        }
        return root;
    }

    /**
     * Definition for a binary tree node.
     */
    private class TreeNode {
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
