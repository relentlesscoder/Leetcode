package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.CompleteBinaryTreeInserter;
import org.wshuai.leetcode.TreeNode;

public class CompleteBinaryTreeInserterTest {
	@Test
	public void testcase(){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		CompleteBinaryTreeInserter cbtt = new CompleteBinaryTreeInserter(root);
		cbtt.insert(7);
		cbtt.insert(8);
		TreeNode r = cbtt.get_root();
	}
}
