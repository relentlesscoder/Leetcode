package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/15/2016.
 * #0314 https://leetcode.com/problems/binary-tree-vertical-order-traversal/
 */
public class BinaryTreeVerticalOrderTraversal {

	// time O(n), space O(n)
	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Map<Integer, ArrayList<Integer>> columnMap = new HashMap<>();
		Deque<TreeNode> nodes = new ArrayDeque<>();
		Deque<Integer> cols = new ArrayDeque<>();
		int minCol = 0, maxCol = 0;
		nodes.offer(root);
		cols.offer(0);
		while (!nodes.isEmpty()) {
			TreeNode curr = nodes.poll();
			int column = cols.poll();
			columnMap.putIfAbsent(column, new ArrayList<>());
			columnMap.get(column).add(curr.val);
			if (curr.left != null) {
				nodes.offer(curr.left);
				cols.offer(column - 1);
				minCol = Math.min(minCol, column - 1);
			}
			if (curr.right != null) {
				nodes.offer(curr.right);
				cols.offer(column + 1);
				maxCol = Math.max(maxCol, column + 1);
			}
		}
		for (int i = minCol; i <= maxCol; i++) {
			res.add(columnMap.get(i));
		}
		return res;
	}
}
