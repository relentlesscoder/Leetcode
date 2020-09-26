package org.wshuai.leetcode;

/**
 * Created by Wei on 01/23/2016.
 * #0235 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestorOfABinaryTree {

	// time O(n)
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null || root == p || root == q){
			return root;
		}
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if(left == null){
			return right;
		}
		if(right == null){
			return left;
		}
		return root;
	}
}
