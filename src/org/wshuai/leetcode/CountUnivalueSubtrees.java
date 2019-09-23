package org.wshuai.leetcode;

/**
 * Created by Wei on 11/13/16.
 * #250 https://leetcode.com/problems/count-univalue-subtrees/
 */
public class CountUnivalueSubtrees {
	//O(n), recursive
	public int countUnivalSubtrees(TreeNode root) {
		CountObj co = new CountObj();
		countUnivalSubtreesUtil(root, co);
		return co.count;
	}

	private boolean countUnivalSubtreesUtil(TreeNode root, CountObj co) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			co.count++;
			return true;
		}
		boolean left = countUnivalSubtreesUtil(root.left, co);
		boolean right = countUnivalSubtreesUtil(root.right, co);
		//Criteria: both left and right subtree are univalue subtree
		// and root.left.val = root.right.val == root.val
		if (left && right && (root.left == null || root.left.val == root.val)
				&& (root.right == null || root.right.val == root.val)) {
			co.count++;
			return true;
		}
		return false;
	}
}
