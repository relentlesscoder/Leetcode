package org.wshuai.leetcode;

/**
 * Created by Wei on 01/13/2020.
 * #0098 https://leetcode.com/problems/validate-binary-search-tree/
 */
public class ValidateBinarySearchTree {
	private TreeNode last = null;
	private boolean res = true;

	// time O(n), space O(n)
	public boolean isValidBST(TreeNode root) {
		inorder(root);
		return res;
	}

	private void inorder(TreeNode root) {
		if (root == null || !res) {
			return;
		}
		inorder(root.left);
		if (last != null && root.val <= last.val) {
			res = false;
		}
		last = root;
		inorder(root.right);
	}

	// time O(n), space O(1)
	public boolean isValidBSTMorris(TreeNode root) {
		TreeNode last = null, curr = root;
		while (curr != null) {
			if (curr.left == null) {
				if (last != null && curr.val <= last.val) {
					return false;
				}
				last = curr;
				curr = curr.right;
			} else {
				TreeNode pre = curr.left;
				while (pre.right != null && pre.right != curr) {
					pre = pre.right;
				}
				if (pre.right == null) {
					pre.right = curr;
					curr = curr.left;
				} else {
					pre.right = null;
					if (last != null && curr.val <= last.val) {
						return false;
					}
					last = curr;
					curr = curr.right;
				}
			}
		}
		return true;
	}

	/**
     * Definition for a binary tree node.
     */
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
