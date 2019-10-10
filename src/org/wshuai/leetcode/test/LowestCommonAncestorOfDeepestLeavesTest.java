package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LowestCommonAncestorOfDeepestLeaves;
import org.wshuai.leetcode.TreeNode;

public class LowestCommonAncestorOfDeepestLeavesTest {
	@Test
	public void testcase() {
		LowestCommonAncestorOfDeepestLeaves lca = new LowestCommonAncestorOfDeepestLeaves();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		TreeNode n = lca.lcaDeepestLeaves(root);
	}
}
