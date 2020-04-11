package org.wshuai.leetcode;

/**
 * Created by Wei on 03/13/2020.
 * #1379 https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
 */
public class FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {
	private TreeNode res;

	// time O(n)
	public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
		res = null;
		dfs(original, cloned, target);
		return res;
	}

	private void dfs(TreeNode original, TreeNode cloned, TreeNode target){
		if(original == null){
			return;
		}
		if(original == target){
			res = cloned;
			return;
		}
		dfs(original.left, cloned.left, target);
		dfs(original.right, cloned.right, target);
	}
}
