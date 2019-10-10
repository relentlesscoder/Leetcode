package org.wshuai.leetcode;

/**
 * Created by Wei on 2/20/17.
 * #222 https://leetcode.com/problems/count-complete-tree-nodes/
 */
public class CountCompleteTreeNodes {
	//109 ms
	public int countNodes(TreeNode root) {
		TreeNode left = root;
		TreeNode right = root;
		int lh = 0;
		int rh = 0;
		while (left != null) {
			lh++;
			left = left.left;
		}
		while (right != null) {
			rh++;
			right = right.right;
		}
		if (lh == rh) {
			return (1 << lh) - 1; //(int)Math.pow(2, lh)-1
		}
		return countNodes(root.left) + countNodes(root.right) + 1;
	}
}
