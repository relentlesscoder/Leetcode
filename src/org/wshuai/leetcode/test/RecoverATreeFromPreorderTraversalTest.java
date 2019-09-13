package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.RecoverATreeFromPreorderTraversal;
import org.wshuai.leetcode.TreeNode;

public class RecoverATreeFromPreorderTraversalTest {
    @Test
    public void testcase(){
        RecoverATreeFromPreorderTraversal rt = new RecoverATreeFromPreorderTraversal();
        TreeNode node = rt.recoverFromPreorder("1-2--3--4-5--6--7");
    }
}
