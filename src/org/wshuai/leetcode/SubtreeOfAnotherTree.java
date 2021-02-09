package org.wshuai.leetcode;

/**
 * Created by Wei on 07/25/2017.
 * #0572 https://leetcode.com/problems/subtree-of-another-tree/
 */
public class SubtreeOfAnotherTree {

	// time O(m*n)
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if(isSame(s, t)){
			return true;
		}
		if(s == null){
			return false;
		}
		return isSubtree(s.left, t) || isSubtree(s.right, t);
	}

	private boolean isSame(TreeNode s, TreeNode t){
		if(s == null && t == null){
			return true;
		}
		if(s == null || t == null){
			return false;
		}
		if(s.val != t.val){
			return false;
		}
		return isSame(s.left, t.left) && isSame(s.right, t.right);
	}
}
