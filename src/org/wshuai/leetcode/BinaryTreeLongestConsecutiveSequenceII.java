package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/31/2019.
 * #0549 https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/
 */
public class BinaryTreeLongestConsecutiveSequenceII {

	private int max;

	// time O(n)
	public int longestConsecutive(TreeNode root) {
		max = 0;
		dfs(root);
		return max;
	}

	private int[] dfs(TreeNode root) {
		if (root == null) {
			return new int[2];
		}
		// 0 - inc, 1 - dec
		int[] left = dfs(root.left);
		int[] right = dfs(root.right);
		int inc = 1, dec = 1;
		if (root.left != null) {
			if (root.val - root.left.val == 1) {
				inc = left[0] + 1;
			} else if (root.val - root.left.val == -1) {
				dec = left[1] + 1;
			}
		}
		if (root.right != null) {
			if (root.val - root.right.val == 1) {
				inc = Math.max(right[0] + 1, inc);
			} else if (root.val - root.right.val == -1) {
				dec = Math.max(right[1] + 1, dec);
			}
		}
		max = Math.max(max, inc + dec - 1);
		return new int[]{inc, dec};
	}
}
