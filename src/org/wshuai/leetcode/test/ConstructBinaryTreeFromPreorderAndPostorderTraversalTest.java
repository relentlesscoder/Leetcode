package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ConstructBinaryTreeFromPreorderAndPostorderTraversal;
import org.wshuai.leetcode.TreeNode;

public class ConstructBinaryTreeFromPreorderAndPostorderTraversalTest {
    @Test
    public void testcase(){
        ConstructBinaryTreeFromPreorderAndPostorderTraversal cb = new ConstructBinaryTreeFromPreorderAndPostorderTraversal();
        TreeNode root = cb.constructFromPrePost(new int[]{1,2,4,5,3,6,7}, new int[]{4,5,2,6,7,3,1});
    }
}
