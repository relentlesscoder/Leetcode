package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Wei on 7/19/17.
 * #637 https://leetcode.com/problems/average-of-levels-in-binary-tree/
 */
public class AverageOfLevelsInBinaryTree {
	//Tree Level Order Traversal
	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> res = new ArrayList<Double>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> curr = new LinkedList<TreeNode>();
		Queue<TreeNode> next = new LinkedList<TreeNode>();
		curr.offer(root);
		int cnt = 0;
		long total = 0;
		while (!curr.isEmpty()) {
			TreeNode nxt = curr.poll();
			cnt++;
			total += nxt.val;
			if (nxt.left != null) {
				next.offer(nxt.left);
			}
			if (nxt.right != null) {
				next.offer(nxt.right);
			}
			if (curr.isEmpty()) {
				curr = next;
				next = new LinkedList<TreeNode>();
				res.add((double) total / (double) cnt);
				cnt = 0;
				total = 0;
			}
		}
		return res;
	}
}
