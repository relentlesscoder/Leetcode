package org.wshuai.leetcode;

/**
 * Created by Wei on 1/19/2016.
 */
public class TreeNode {
	int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int _val) {
		val = _val;
	}

	public TreeNode(int _val, TreeNode _left, TreeNode _right) {
		val = _val;
		left = _left;
		right = _right;
	}
}
