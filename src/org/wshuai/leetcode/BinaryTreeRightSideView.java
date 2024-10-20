package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 11/10/2016.
 * #0199 https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView {

	// time O(n), space O(n/2)
	public List<Integer> rightSideViewBFS(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size > 0) {
				TreeNode curr = queue.poll();
				if (--size == 0) {
					res.add(curr.val);
				}
				if (curr.left != null) {
					queue.offer(curr.left);
				}
				if (curr.right != null) {
					queue.offer(curr.right);
				}
			}
		}
		return res;
	}

	// time O(n), space O(h)
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		dfs(root, 0, res);
		return res;
	}

	private void dfs(TreeNode node, int level, List<Integer> res) {
		if (level == res.size()) {
			res.add(node.val);
		}
		if (node.right != null) {
			dfs(node.right, level + 1, res);
		}
		if (node.left != null) {
			dfs(node.left, level + 1, res);
		}
	}
}
