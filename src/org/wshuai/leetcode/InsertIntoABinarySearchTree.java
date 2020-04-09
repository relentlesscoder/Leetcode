package org.wshuai.leetcode;

/**
 * Created by Wei on 08/30/2019.
 * #0701 https://leetcode.com/problems/insert-into-a-binary-search-tree/
 */
public class InsertIntoABinarySearchTree {
	// time O(log(n))
	// book CLRS, page 294
	public TreeNode insertIntoBST(TreeNode root, int val) {
		TreeNode node = new TreeNode(val), cur = root, parent = null;
		while (cur != null) {
			parent = cur;
			if (cur.val > val) {
				cur = cur.left;
			} else {
				cur = cur.right;
			}
		}
		if (parent == null) {
			return node;
		}
		if (parent.val > val) {
			parent.left = node;
		} else {
			parent.right = node;
		}
		return root;
	}
}
