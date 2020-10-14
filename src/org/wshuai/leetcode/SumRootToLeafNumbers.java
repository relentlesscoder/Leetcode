package org.wshuai.leetcode;

/**
 * Created by Wei on 10/29/2016.
 * #0129 https://leetcode.com/problems/sum-root-to-leaf-numbers/
 */
public class SumRootToLeafNumbers {

	// time O(n)
	public int sumNumbers(TreeNode root) {
		if(root == null){
			return 0;
		}
		return dfs(root, 0);
	}

	private int dfs(TreeNode root, int cur){
		cur = cur * 10 + root.val;
		if(root.left == null && root.right == null){
			return cur;
		}
		return (root.left == null ? 0 : dfs(root.left, cur))
				+ (root.right == null ? 0 : dfs(root.right, cur));
	}
}
