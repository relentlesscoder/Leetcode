package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.BinaryTreeLongestConsecutiveSequenceII;
import org.wshuai.leetcode.TreeNode;

public class BinaryTreeLongestConsecutiveSequenceIITest {
	@Test
	public void testcase1(){
		BinaryTreeLongestConsecutiveSequenceII btl = new BinaryTreeLongestConsecutiveSequenceII();
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);
		int res = btl.longestConsecutive(root);
	}

	@Test
	public void testcase2(){
		BinaryTreeLongestConsecutiveSequenceII btl = new BinaryTreeLongestConsecutiveSequenceII();
		TreeNode root = new TreeNode(3);
		root.right = new TreeNode(1);
		root.right.left = new TreeNode(2);
		int res = btl.longestConsecutive(root);
	}
}
