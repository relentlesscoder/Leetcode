package org.wshuai.leetcode;

/**
 * Created by Wei on 01/14/2020.
 * #0100 https://leetcode.com/problems/same-tree/
 */
public class SameTree {
	// time O(n)
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q == null){
			return true;
		}
		if(p == null || q == null){
			return false;
		}
		return p.val == q.val
			&& isSameTree(p.left, q.left)
			&& isSameTree(p.right, q.right);
	}
}
