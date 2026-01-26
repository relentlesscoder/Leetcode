package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 02/20/2017.
 * #0515 https://leetcode.com/problems/find-largest-value-in-each-tree-row/
 */
public class FindLargestValueInEachTreeRow {

	// time O(n), space O(n)
	public List<Integer> largestValuesRecursuve(TreeNode root) {
		// #0199 相同思路
		List<Integer> res = new ArrayList<>();
		dfs(root, 0, res);
		return res;
	}

	private void dfs(TreeNode root, int depth, List<Integer> res) {
		if (root == null) {
			return;
		}
		if (res.size() == depth) { // 将每一层第一个节点的值加入答案队列
			res.add(root.val);
		} else { // 找到当前层的最大值
			res.set(depth, Math.max(root.val, res.get(depth)));
		}
		dfs(root.left, depth + 1, res);
		dfs(root.right, depth + 1, res);
	}

	// time O(n), space O(n)
	public List<Integer> largestValuesBFS(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			// 将每一层第一个节点的值加入答案队列
			res.add(queue.peek().val);
			int size = queue.size();
			while (size-- > 0) {
				TreeNode node = queue.poll();
				int idx = res.size() - 1;
				// 找到当前层的最大值
				res.set(idx, Math.max(res.get(idx), node.val));
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
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
