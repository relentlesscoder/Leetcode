package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Wei on 10/29/16.
 * #437 https://leetcode.com/problems/path-sum-iii/
 */
public class PathSumIII {
	private int res;

	public int pathSum(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		res = 0;
		List<TreeNode> lst = new ArrayList<TreeNode>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			lst.add(node);
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		for (TreeNode node : lst) {
			pathSumUtil(node, sum, 0);
		}
		return res;
	}

	public void pathSumUtil(TreeNode root, int sum, int csum) {
		if (root == null) {
			return;
		}
		int nsum = csum + root.val;
		if (nsum == sum) {
			res++;
		}

		pathSumUtil(root.left, sum, nsum);
		pathSumUtil(root.right, sum, nsum);
	}
}