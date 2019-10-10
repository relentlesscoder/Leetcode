package org.wshuai.leetcode;

/**
 * Created by Wei on 9/20/2016.
 * #270 https://leetcode.com/problems/closest-binary-search-tree-value/
 */
public class ClosestBinarySearchTreeValue {
	public int closestValue(TreeNode root, double target) {
		int val = 0;
		double min = Double.MAX_VALUE;

		while (root != null) {
			int rVal = root.val;

			double diff = Math.abs(rVal - target);
			if (diff < min) {
				min = diff;
				val = root.val;
			}

			if (rVal > target) {
				root = root.left;
			} else {
				root = root.right;
			}
		}

		return val;
	}
}
