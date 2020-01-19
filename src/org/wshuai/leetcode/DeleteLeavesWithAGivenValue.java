package org.wshuai.leetcode;

/**
 * Created by Wei on 01/18/2020.
 * #0000 https://leetcode.com/problems/delete-leaves-with-a-given-value/
 */
public class DeleteLeavesWithAGivenValue {
	// time O(n)
	public TreeNode removeLeafNodes(TreeNode root, int target) {
		if(dfs(root, target)){
			return null;
		}
		return root;
	}

	private boolean dfs(TreeNode root, int target){
		if(root == null){
			return true;
		}
		boolean left = dfs(root.left, target);
		boolean right = dfs(root.right, target);
		if(left){
			root.left = null;
		}
		if(right){
			root.right = null;
		}
		return root.val == target && left && right;
	}
}
