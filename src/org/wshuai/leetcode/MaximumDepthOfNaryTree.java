package org.wshuai.leetcode;

/**
 * Created by Wei on 8/9/19.
 * #559 https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 */
public class MaximumDepthOfNaryTree {
	public int maxDepth(NaryTreeNode root) {
		return DFS(root, 0);
	}

	private int DFS(NaryTreeNode root, int curr) {
		if (root == null) {
			return 0;
		}
		curr++;
		if (root.children == null || root.children.size() == 0) {
			return curr;
		}
		int max = 0;
		for (NaryTreeNode child : root.children) {
			int depth = DFS(child, curr);
			max = max > depth ? max : depth;
		}
		return max;
	}
}
