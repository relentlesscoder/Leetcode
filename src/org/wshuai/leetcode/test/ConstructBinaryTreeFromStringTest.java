package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ConstructBinaryTreeFromString;
import org.wshuai.leetcode.TreeNode;

public class ConstructBinaryTreeFromStringTest {
	@Test
	public void testcase(){
		ConstructBinaryTreeFromString cbt = new ConstructBinaryTreeFromString();
		TreeNode root = cbt.str2tree("-4(2(3)(1))(6(5)(7))");
	}
}
