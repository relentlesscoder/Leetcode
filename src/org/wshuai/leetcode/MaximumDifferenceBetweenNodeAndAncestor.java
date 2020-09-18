package org.wshuai.leetcode;

/**
 * Created by Wei on 09/05/2019.
 * #1026 https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
 */
public class MaximumDifferenceBetweenNodeAndAncestor {

	private int res;

	// time O(n)
	public int maxAncestorDiff(TreeNode root) {
		res = 0;
		dfs(root.left, root.val, root.val);
		dfs(root.right, root.val, root.val);
		return res;
	}

	private void dfs(TreeNode cur, int min, int max){
		if(cur == null){
			return;
		}
		res = Math.max(res, Math.max(Math.abs(cur.val - min), Math.abs(cur.val - max)));
		dfs(cur.left, Math.min(min, cur.val), Math.max(max, cur.val));
		dfs(cur.right, Math.min(min, cur.val), Math.max(max, cur.val));
	}
}
