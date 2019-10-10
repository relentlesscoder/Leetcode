package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.BinaryTreeVerticalOrderTraversal;
import org.wshuai.leetcode.TreeNode;

import java.util.List;

/**
 * Created by Wei on 11/15/16.
 */
public class BinaryTreeVerticalOrderTraversalTest {
	@Test
	public void testcase() {
		BinaryTreeVerticalOrderTraversal bt = new BinaryTreeVerticalOrderTraversal();
		TreeNode root = new TreeNode(1);
		List<List<Integer>> r = bt.verticalOrder(root);
	}
}
