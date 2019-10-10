package org.wshuai.leetcode;

/**
 * Created by Wei on 7/24/2017.
 * #563 https://leetcode.com/problems/binary-tree-tilt/
 */
public class BinaryTreeTilt {
	public int findTilt(TreeNode root) {
		int[] res = new int[1];
		findTiltUtil(root, res);
		return res[0];
	}

	private int findTiltUtil(TreeNode root, int[] res) {
		if (root == null) {
			return 0;
		}
		int left = findTiltUtil(root.left, res);
		int right = findTiltUtil(root.right, res);
		res[0] += Math.abs(left - right);
		int sum = left + right;
		sum += root.val;
		return sum;
	}
}
