package org.wshuai.leetcode;

/**
 * Created by Wei on 01/15/2020.
 * #0111 https://leetcode.com/problems/minimum-depth-of-binary-tree/
 */
public class MinimumDepthOfBinaryTree {
	public int minDepth(TreeNode root) {
		if(root == null){
			return 0;
		}
		int left = minDepth(root.left);
		int right = minDepth(root.right);
		// node has only one child nodes is not leave node
		if(root.left == null){
			return right + 1;
		}
		if(root.right == null){
			return left + 1;
		}
		return 1 + Math.min(left, right);
	}
}
