package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.CountCompleteTreeNodes;
import org.wshuai.leetcode.TreeNode;

/**
 * Created by Wei on 2/20/17.
 */
public class CountCompleteTreeNodesTest {
	@Test
	public void test() {
		CountCompleteTreeNodes cc = new CountCompleteTreeNodes();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		int cnt = cc.countNodes(root);
	}
}
