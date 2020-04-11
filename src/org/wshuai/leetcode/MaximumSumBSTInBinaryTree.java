package org.wshuai.leetcode;

/**
 * Created by Wei on 03/09/2020.
 * #1373 https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
 */
public class MaximumSumBSTInBinaryTree {

	private int res;

	// time O(n)
	public int maxSumBST(TreeNode root) {
		res = 0;
		isBST(root);
		return res;
	}

	private int[] isBST(TreeNode root){
		if(root == null){
			// bst?, sum, min, max
			return new int[]{1, 0, Integer.MAX_VALUE, Integer.MIN_VALUE};
		}
		int[] left = isBST(root.left);
		int[] right = isBST(root.right);
		// if current subtree is not a BST, all parents is not BST
		if(left[0] == 0 || right[0] == 0 || root.val <= left[3] || root.val >= right[2]){
			return new int[]{0, -1, -1, -1};
		}
		int sum = root.val + left[1] + right[1];
		int min = left[2] == Integer.MAX_VALUE ? root.val : left[2];
		int max = right[3] == Integer.MIN_VALUE ? root.val : right[3];
		res = Math.max(res, sum);
		return new int[]{1, sum, min, max};
	}
}
