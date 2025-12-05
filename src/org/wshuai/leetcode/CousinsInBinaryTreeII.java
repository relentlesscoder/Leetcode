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
    public TreeNode replaceValueInTree(TreeNode root) {
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
        root.val = 0;
        dfs(root, 0, levelSum);
        return root;
    }

    private void dfs(TreeNode root, int level, List<Integer> levelSum) {
        int childSum = 0;
        if (root.left != null) {
            childSum += root.left.val;
        }
        if (root.right != null) {
            childSum += root.right.val;
        }
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
