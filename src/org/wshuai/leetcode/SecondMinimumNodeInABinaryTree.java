package org.wshuai.leetcode;

/**
 * Created by Wei on 08/10/2019.
 * #0671 https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 */
public class SecondMinimumNodeInABinaryTree {
	private long min, res;

	// time O(n)
	public int findSecondMinimumValue(TreeNode root) {
		min = res = Long.MAX_VALUE;
		dfs(root);
		return res == Long.MAX_VALUE ? -1 : (int)res;
	}

	private void dfs(TreeNode root){
		if(root == null){
			return;
		}
		if(root.val < min){
			res = min;
			min = root.val;
		}else if(root.val != min && root.val < res){
			res = root.val;
		}
		dfs(root.left);
		dfs(root.right);
	}
}
