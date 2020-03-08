package org.wshuai.leetcode;

/**
 * Created by Wei on 01/23/2016.
 * #0235 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestorOfABinarySearchTree {
	// time O(n)
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root.val < p.val && root.val < q.val){
			return lowestCommonAncestor(root.right, p, q);
		}
		if(root.val > p.val && root.val > q.val){
			return lowestCommonAncestor(root.left, p, q);
		}
		return root;
	}
}
