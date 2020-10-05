package org.wshuai.leetcode;

/**
 * Created by Wei on 10/29/2016.
 * #0129 https://leetcode.com/problems/sum-root-to-leaf-numbers/
 */
public class SumRootToLeafNumbers {

	private int sum;

	// time O(n)
	public int sumNumbers(TreeNode root) {
		if(root == null){
			return 0;
		}
		sum = 0;
		dfs(root, 0);
		return sum;
	}

	private void dfs(TreeNode root, int cur){
		cur = cur * 10 + root.val;
		if(root.left == null && root.right == null){
			sum += cur;
			return;
		}
		if(root.left != null){
			dfs(root.left, cur);
		}
		if(root.right != null){
			dfs(root.right, cur);
		}
	}
}
