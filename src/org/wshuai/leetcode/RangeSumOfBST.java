package org.wshuai.leetcode;

/**
 * Created by Wei on 8/7/19.
 * #938 https://leetcode.com/problems/range-sum-of-bst/
 */
public class RangeSumOfBST {
	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null) {
			return 0;
		}
		int sum = 0;

		if (root.val < L) {
			sum += rangeSumBST(root.right, L, R);
		} else if (root.val > R) {
			sum += rangeSumBST(root.left, L, R);
		} else {
			sum += root.val;
			sum += rangeSumBST(root.right, L, R);
			sum += rangeSumBST(root.left, L, R);
		}

		return sum;
	}
}
