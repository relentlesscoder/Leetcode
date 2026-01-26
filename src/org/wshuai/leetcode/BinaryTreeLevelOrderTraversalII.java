package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei on 09/19/2016.
 * #0107 https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 */
public class BinaryTreeLevelOrderTraversalII {

	// time O(n), space O(n)
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		LinkedList<List<Integer>> res = new LinkedList<>();
		if (root == null) {
			return res;
		}
		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			List<Integer> curr = new ArrayList<>();
			int size = queue.size();
			while (size-- > 0) {
				TreeNode node = queue.poll();
				curr.add(node.val);
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			res.offerFirst(curr);
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
