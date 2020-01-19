package org.wshuai.leetcode;

/**
 * Created by Wei on 1/19/2016.
 * #0104 https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class MaximumDepthOfBinaryTree {
	// time O(n)
	public int maxDepth(TreeNode root) {
		if(root == null){
			return 0;
		}
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		return Math.max(left, right) + 1;
	}
}
