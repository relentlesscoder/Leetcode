package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.PrintBinaryTree;
import org.wshuai.leetcode.TreeNode;

import java.util.List;

public class PrintBinaryTreeTest {
	@Test
	public void testcase(){
		PrintBinaryTree pbt = new PrintBinaryTree();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(4);
		List<List<String>> res = pbt.printTree(root);
	}
}
