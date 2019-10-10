package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LongestUnivaluePath;
import org.wshuai.leetcode.TreeNode;

public class LongestUnivaluePathTest {
	@Test
	public void testcase() {
		LongestUnivaluePath lu = new LongestUnivaluePath();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(4);
		root.right = new TreeNode(5);
		root.right.left = new TreeNode(5);
		int max = lu.longestUnivaluePath(root);
	}
}
