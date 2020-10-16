package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2019.
 * #1123 https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 */
public class LowestCommonAncestorOfDeepestLeaves {

	// time O(n)
	public TreeNode lcaDeepestLeaves(TreeNode root) {
		return dfs(root).node;
	}

	private Pair dfs(TreeNode root){
		if(root == null){
			return new Pair(null, 0);
		}
		Pair left = dfs(root.left), right = dfs(root.right);
		int leftDepth = left.depth, rightDepth = right.depth;
		return new Pair(leftDepth == rightDepth ? root : leftDepth > rightDepth ? left.node : right.node,
				1 + Math.max(leftDepth, rightDepth));
	}

	private class Pair{

		private TreeNode node;

		private int depth;

		private Pair(TreeNode node, int depth){
			this.node = node;
			this.depth = depth;
		}
	}
}
