package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Wei on 10/28/2016.
 * #298 https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
 */
public class BinaryTreeLongestConsecutiveSequence {

	private int res;

	public int longestConsecutiveDFS(TreeNode root) {
		res = 0;
		dfs(root);
		return res;
	}

	private int dfs(TreeNode root){
		if(root == null){
			return 0;
		}

		if(root.left==null && root.right==null){
			res = Math.max(res, 1);
			return 1;
		}

		int leftMax = dfs(root.left);
		int left = root.left != null && root.val == root.left.val - 1 ? leftMax + 1 : 1;

		int rightMax = dfs(root.right);
		int right = root.right != null && root.val == root.right.val - 1 ? rightMax + 1 : 1;

		int curr = Math.max(left, right);
		res = Math.max(res, curr);
		return curr;
	}

	// BFS
	public int longestConsecutiveBFS(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int max = 1;
		Queue<TreeNode> nodes = new LinkedList<TreeNode>();
		Queue<Integer> sizes = new LinkedList<Integer>();
		nodes.offer(root);
		sizes.offer(1);
		while (!nodes.isEmpty()) {
			TreeNode node = nodes.poll();
			int size = sizes.poll();
			if (node.left != null) {
				int ns = 1;
				if (node.val == node.left.val - 1) {
					ns = size + 1;
					max = ns > max ? ns : max;
				}
				sizes.offer(ns);
				nodes.offer(node.left);
			}

			if (node.right != null) {
				int ns = 1;
				if (node.val == node.right.val - 1) {
					ns = size + 1;
					max = ns > max ? ns : max;
				}
				sizes.offer(ns);
				nodes.offer(node.right);
			}
		}

		return max;
	}
}
