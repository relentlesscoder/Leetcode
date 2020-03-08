package org.wshuai.leetcode;

/**
 * Created by Wei on 10/28/2016.
 * #0298 https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
 */
public class BinaryTreeLongestConsecutiveSequence {
	private int res;

	// time O(n)
	public int longestConsecutive(TreeNode root) {
		if(root == null){
			return 0;
		}
		res = 1;
		dfs(root.left, root, 1);
		dfs(root.right, root, 1);
		return res;
	}

	private void dfs(TreeNode cur, TreeNode parent, int n){
		if(cur == null){
			return;
		}
		n = parent.val + 1 == cur.val ? n + 1 : 1;
		res = Math.max(res, n);
		dfs(cur.left, cur, n);
		dfs(cur.right, cur, n);
	}
}
