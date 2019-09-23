package org.wshuai.leetcode;

/**
 * Created by Wei on 8/8/19.
 * #700 https://leetcode.com/problems/search-in-a-binary-search-tree/
 */
public class SearchInABinarySearchTree {
	public TreeNode searchBST(TreeNode root, int val) {
		if (root == null) {
			return null;
		}
		if (root.val == val) {
			return root;
		} else if (root.val < val) {
			return searchBST(root.right, val);
		} else {
			return searchBST(root.left, val);
		}
	}
}
