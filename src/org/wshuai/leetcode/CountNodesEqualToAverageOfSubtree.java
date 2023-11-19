package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2023.
 * #2265 https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/
 */
public class CountNodesEqualToAverageOfSubtree {

	// time O(n), space O(n)
	public int averageOfSubtree(TreeNode root) {
		int[] res = dfs(root);
		return res[2];
	}

	private int[] dfs(TreeNode curr) {
		if (curr == null) {
			return new int[] {0, 0, 0};
		}
		int[] left = dfs(curr.left);
		int[] right = dfs(curr.right);
		int[] res = new int[3];
		res[0] = curr.val + left[0] + right[0];
		res[1] = 1 + left[1] + right[1];
		res[2] = (res[0] / res[1] == curr.val ? 1 : 0) + left[2] + right[2];
		return res;
	}
}
