package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.VerifyPreorderSerializationOfABinaryTree;

/**
 * Created by Wei on 9/24/2016.
 */
public class VerifyPreorderSerializationOfABinaryTreeTest {
	@Test
	public void testcase() {
		VerifyPreorderSerializationOfABinaryTree v = new VerifyPreorderSerializationOfABinaryTree();
		boolean x = v.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
	}
}
