package org.wshuai.leetcode;

/**
 * Created by Wei on 08/09/2019.
 * #0669 https://leetcode.com/problems/trim-a-binary-search-tree/
 */
public class TrimABinarySearchTree {
	// time O(n)
	public TreeNode trimBST(TreeNode root, int L, int R) {
		if (root == null) {
			return null;
		}
		TreeNode left = trimBST(root.left, L, R);
		TreeNode right = trimBST(root.right, L, R);
		if (root.val < L) {
			return right;
		}
		if (root.val > R) {
			return left;
		}
		root.left = left;
		root.right = right;
		return root;
	}
}
