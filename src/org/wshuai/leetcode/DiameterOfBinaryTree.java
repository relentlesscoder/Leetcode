package org.wshuai.leetcode;

/**
 * Created by Wei on 4/3/17.
 * #543 https://leetcode.com/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTree {
	public int diameterOfBinaryTree(TreeNode root) {
		int[] max = new int[1];
		maxDepth(root, max);
		return max[0];
	}

	private int maxDepth(TreeNode root, int[] max) {
		if (root == null) {
			return 0;
		}
		int left = maxDepth(root.left, max);
		int right = maxDepth(root.right, max);
		max[0] = Math.max(max[0], left + right);
		return Math.max(left, right) + 1;
	}
}
