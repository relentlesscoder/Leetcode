package org.wshuai.leetcode;

/**
 * Created by Wei on 8/9/19.
 * #965 https://leetcode.com/problems/univalued-binary-tree/
 */
public class UnivaluedBinaryTree {
	public boolean isUnivalTree(TreeNode root) {
		if (root == null) {
			return true;
		}
		return (root.left == null || root.left.val == root.val)
				&& (root.right == null || root.right.val == root.val)
				&& isUnivalTree(root.left)
				&& isUnivalTree(root.right);
	}
}
