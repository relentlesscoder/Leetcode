package org.wshuai.leetcode;

/**
 * Created by Wei on 8/10/19.
 * #671 https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 */
public class SecondMinimumNodeInABinaryTree {
	public int findSecondMinimumValue(TreeNode root) {
		if (root == null) {
			return -1;
		}
		long[] mins = new long[2];
		mins[0] = Long.MAX_VALUE;
		mins[1] = Long.MAX_VALUE;
		findSecondMinimumValue(root, mins);
		return mins[1] == Long.MAX_VALUE ? -1 : (int) mins[1];
	}

	private void findSecondMinimumValue(TreeNode root, long[] mins) {
		if (root == null) {
			return;
		}
		int min = root.val;
		if (min < mins[0]) {
			mins[1] = mins[0];
			mins[0] = min;
		} else if (min != mins[0] && min < mins[1]) {
			mins[1] = min;
		}
		findSecondMinimumValue(root.left, mins);
		findSecondMinimumValue(root.right, mins);
	}
}
