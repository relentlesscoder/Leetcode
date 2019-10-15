package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ClosestLeafInABinaryTree;
import org.wshuai.leetcode.TreeNode;

public class ClosestLeafInABinaryTreeTest {
	@Test
	public void testcase(){
		ClosestLeafInABinaryTree cli = new ClosestLeafInABinaryTree();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(3);
		root.right = new TreeNode(2);
		int min = cli.findClosestLeaf(root, 1);
	}
}
