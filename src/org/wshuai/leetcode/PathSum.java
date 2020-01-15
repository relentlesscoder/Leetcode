package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0112 https://leetcode.com/problems/path-sum/
 */
public class PathSum {
	// time O(n)
	public boolean hasPathSum(TreeNode root, int sum) {
		if(root == null){
			return false;
		}
		if(root.left == null
				&& root.right == null
				&& sum == root.val){
			return true;
		}
		return hasPathSum(root.left, sum - root.val)
				|| hasPathSum(root.right, sum - root.val);
	}
}
