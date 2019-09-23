package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.MaximumDifferenceBetweenNodeAndAncestor;
import org.wshuai.leetcode.TreeNode;

public class MaximumDifferenceBetweenNodeAndAncestorTest {
	@Test
	public void testcase1() {
		MaximumDifferenceBetweenNodeAndAncestor md = new MaximumDifferenceBetweenNodeAndAncestor();
		TreeNode root = new TreeNode(8);
		root.left = new TreeNode(3);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(6);
		root.left.right.left = new TreeNode(4);
		root.left.right.right = new TreeNode(7);
		root.right = new TreeNode(10);
		root.right.right = new TreeNode(14);
		root.right.right.left = new TreeNode(13);
		int max = md.maxAncestorDiff(root);
	}

	@Test
	public void testcase2() {
		MaximumDifferenceBetweenNodeAndAncestor md = new MaximumDifferenceBetweenNodeAndAncestor();
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(2);
		root.right.right = new TreeNode(0);
		root.right.right.left = new TreeNode(3);
		int max = md.maxAncestorDiff(root);
	}
}
