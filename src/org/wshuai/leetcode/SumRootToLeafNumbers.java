package org.wshuai.leetcode;

/**
 * Created by Wei on 10/29/2016.
 * #0129 https://leetcode.com/problems/sum-root-to-leaf-numbers/
 */
public class SumRootToLeafNumbers {
	private int sum;

	// time O(n)
	public int sumNumbers(TreeNode root) {
		sum = 0;
		dfs(root, 0);
		return sum;
	}

	private void dfs(TreeNode root, int cur){
		if(root == null){
			return;
		}
		cur = cur * 10 + root.val;
		if(root.left == null && root.right == null){
			sum += cur;
			return;
		}
		dfs(root.left, cur);
		dfs(root.right, cur);
	}
}
