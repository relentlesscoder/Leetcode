package org.wshuai.leetcode;

/**
 * Created by Wei on 10/14/2019.
 * #0998 https://leetcode.com/problems/maximum-binary-tree-ii/
 */
public class MaximumBinaryTreeII {

	// time O(n), space O(1)
	public TreeNode insertIntoMaxTree(TreeNode root, int val) {
		TreeNode curr = root, parent = null, node = new TreeNode(val);
		while (curr != null && curr.val > val) {
			parent = curr;
			curr = curr.right;
		}
		if (parent == null) {
			node.left = root;
			return node;
		} else {
			node.left = parent.right;
			parent.right = node;
			return root;
		}
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
