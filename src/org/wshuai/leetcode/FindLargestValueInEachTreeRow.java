package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Wei on 2/20/17.
 * #515 https://leetcode.com/problems/find-largest-value-in-each-tree-row/
 */
public class FindLargestValueInEachTreeRow {
	//O(n), binary tree level order traversal
	public List<Integer> largestValues(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> curr = new LinkedList<TreeNode>();
		Queue<TreeNode> nxt = new LinkedList<TreeNode>();
		curr.offer(root);
		int max = Integer.MIN_VALUE;
		while (!curr.isEmpty()) {
			TreeNode node = curr.poll();
			max = max < node.val ? node.val : max;
			if (node.left != null) {
				nxt.offer(node.left);
			}
			if (node.right != null) {
				nxt.offer(node.right);
			}
			if (curr.isEmpty()) {
				res.add(max);
				max = Integer.MIN_VALUE;
				curr = nxt;
				nxt = new LinkedList<TreeNode>();
			}
		}
		return res;
	}
}
