package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2017.
 * #0617 https://leetcode.com/problems/merge-two-binary-trees/
 */
public class MergeTwoBinaryTrees {
	//  time O(n)
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return null;
		}
		if (t1 == null) {
			return t2;
		}
		if (t2 == null) {
			return t1;
		}
		TreeNode t = new TreeNode(t1.val + t2.val);
		t.left = mergeTrees(t1.left, t2.left);
		t.right = mergeTrees(t1.right, t2.right);
		return t;
	}
}
