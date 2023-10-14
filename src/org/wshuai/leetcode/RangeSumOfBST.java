package org.wshuai.leetcode;

/**
 * Created by Wei on 08/07/2019.
 * #0938 https://leetcode.com/problems/range-sum-of-bst/
 */
public class RangeSumOfBST {

	// time O(n), space O(n)
	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null) {
			return 0;
		}
		if (root.val >= L && root.val <= R) {
			return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
		}
		if (root.val < L) {
			return rangeSumBST(root.right, L, R);
		} else {
			return rangeSumBST(root.left, L, R);
		}
	}
}
