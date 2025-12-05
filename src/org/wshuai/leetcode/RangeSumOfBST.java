package org.wshuai.leetcode;

/**
 * Created by Wei on 08/07/2019.
 * #0938 https://leetcode.com/problems/range-sum-of-bst/
 */
public class RangeSumOfBST {

	// time O(n), space O(h)
	public int rangeSumBST(TreeNode root, int low, int high) {
		if (root == null) {
			return 0;
		}
		if (root.val >= low && root.val <= high) {
			return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
		} else if (root.val < low) {
			return rangeSumBST(root.right, low, high);
		} else {
			return rangeSumBST(root.left, low, high);
		}
	}
}
