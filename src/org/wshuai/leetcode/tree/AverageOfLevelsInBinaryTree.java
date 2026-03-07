package org.wshuai.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 07/19/2017.
 * #0637 https://leetcode.com/problems/average-of-levels-in-binary-tree/
 */
public class AverageOfLevelsInBinaryTree {

	// time O(n), space O(n)
	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> res = new ArrayList<>();
		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			double sum = 0.0, count = queue.size();
			int size = queue.size();
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
			res.add(sum / count);
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
