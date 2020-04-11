package org.wshuai.leetcode;

/**
 * Created by Wei on 08/09/2019.
 * #0559 https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 */
public class MaximumDepthOfNaryTree {

	// time O(n)
	public int maxDepth(NaryTreeNode root) {
		if (root == null) {
			return 0;
		}
		return dfs(root);
	}

	private int dfs(NaryTreeNode root) {
		if (root == null) {
			return 0;
		}
		int max = 0;
		for (NaryTreeNode child : root.children) {
			max = Math.max(max, dfs(child));
		}
		return max + 1;
	}

}
