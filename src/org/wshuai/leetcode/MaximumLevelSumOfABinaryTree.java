package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 08/30/2019.
 * #1161 https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
 */
public class MaximumLevelSumOfABinaryTree {

    // time O(n), space O(n)
    public int maxLevelSum(TreeNode root) {
        int res = -1, maxSum = Integer.MIN_VALUE, level = 1;
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
            if (sum > maxSum) {
                maxSum = sum;
                res = level;
            }
            level++;
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
