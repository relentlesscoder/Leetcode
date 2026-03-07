package org.wshuai.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Created by Wei on 01/25/2026.
 * #2583 https://leetcode.com/problems/kth-largest-sum-in-a-binary-tree/
 */
public class KthLargestSumInABinaryTree {

    // time O(n * log(k)), space O(n)
    public long kthLargestLevelSum(TreeNode root, int k) {
        // 维护一个大小为 k 的最小堆，BFS 二叉树求每一层的和并放入最小堆中最后堆首即为答案。
        PriorityQueue<Long> minQueue = new PriorityQueue<>();
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
            minQueue.offer(sum);
            if (minQueue.size() > k) {
                minQueue.poll();
            }
        }
        return minQueue.size() == k ? minQueue.peek() : -1;
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
